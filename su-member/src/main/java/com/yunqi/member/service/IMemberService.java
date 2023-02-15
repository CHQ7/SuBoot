package com.yunqi.member.service;

import com.yunqi.member.models.Member;
import com.yunqi.member.models.MemberLevel;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.json.Json;
import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.common.utils.DateUtil;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.security.spi.UserSecurityUtil;
import com.yunqi.starter.wx.spi.Wxs;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户管理
 * Created by @author CHQ on 2022/11/2
 */
@Slf4j
@Service
public class IMemberService extends BaseServiceImpl<Member> {

    @Resource
    IMemberLevelService memberLevelService;

    /**
     * 用户列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<Member> list(QueryBody query ) {
        Cnd cnd =  Cnd.where("delFlag","=",false);
        // 模糊查询:手机号
        if(Strings.isNotBlank(query.getString("mobile"))){
            cnd.and("mobile", "like", "%" + query.getString("mobile") + "%");
        }
        // 精确查询:等级
        if(Strings.isNotBlank(query.getString("levelId"))){
            cnd.and("levelId", "=", query.getInt("levelId"));
        }
        // 精确查询:状态
        if(Strings.isNotBlank(query.getString("status"))){
            cnd.and("status", "=", query.getBoolean("status"));
        }
        // 时间范围:根据时间戳范围查询
        if(Strings.isNotBlank(query.getString("beginTime")) || Strings.isNotBlank(query.getString("endTime"))){
            cnd.and("createdAt",">=", query.getString("beginTime"));
            cnd.and("createdAt","<=", query.getString("endTime"));
        }
        // 创建时间倒序
        cnd.desc("createdAt");

        Pagination<Member> pagination = this.listPage(query.page(),query.pageSize(), cnd);
        List<Member> list = pagination.getList();
        for (Member member :list) {

            // -------------------------------------------------------------------------
            // 计算年龄
            // -------------------------------------------------------------------------
            if(Strings.isNotBlank(member.getBirthday())){
                String dateStr = DateUtil.format(DateUtil.parse(member.getBirthday()), "yyyy-MM-dd");
                // 过滤超出时间范围则不计算年龄
                if(DateUtil.parse(dateStr).before(DateUtil.date())){
                    member.setAge(DateUtil.ageOfNow(dateStr));
                }
            }

            // -------------------------------------------------------------------------
            // 获取等级
            // -------------------------------------------------------------------------
            MemberLevel level =  memberLevelService.fetchById(member.getLevelId());
            member.setLevel(level);
        }
        pagination.setList(list);
        return pagination;
    }


    /**
     * 创建用户
     * @param member    新的用户
     * @return          用户
     */
    @Transactional
    public Member create(Member member) {
        return this.insert(member);
    }


    /**
     * 更新用户
     * @param member    更新用户
     * @return          boolean
     */
    @Transactional
    public boolean update(Member member) {
        return this.updateIgnoreNull(member) != 0;
    }

    /**
     * 伪删除用户
     * @param id    ID
     * @return      boolean
     */
    @Transactional
    public boolean deleteById(String id) {
        return this.vDelete(id) != 0;
    }

    /**
     * 批量伪删除用户
     * @param ids   ID数组字符串
     */
    @Transactional
    public void deleteByIds(String ids) {
        if(Strings.isNotBlank(ids)){
            String[] arr =  ids.split(",");
            this.vDelete(arr);
        }

    }

    /**
     * 获取用户信息
     * @param id    ID
     * @return      用户信息
     */
    public Member userInfo(String id) {
        return this.fetch(id);
    }

    /**
     * 更改用户昵称
     *
     * @param id        ID
     * @param nickname  用户名
     * @return          boolean
     */
    @Transactional
    public boolean updateNickname(String id, String nickname){
        Member member = this.fetch(id);
        member.setNickname(nickname);
        return this.update(member);
    }

    /**
     * 更改用户性别
     *
     * @param id        ID
     * @param sex       性别
     * @return          boolean
     */
    @Transactional
    public boolean updateSex(String id, int sex){
        Member member = this.fetch(id);
        member.setSex(sex);
        return this.update(member);
    }

    /**
     * 更改用户生日
     *
     * @param id        ID
     * @param birthday  生日
     * @return          boolean
     */
    @Transactional
    public boolean updateBirthday(String id, String birthday){
        Member member = this.fetch(id);
        member.setBirthday(birthday);
        return this.update(member);
    }

    /**
     * 更改用户地区
     *
     * @param id        ID
     * @param province  省
     * @param city      市
     * @param area      区
     * @return          boolean
     */
    @Transactional
    public boolean updateRegion(String id, String province, String city, String area){
        Member member = this.fetch(id);
        member.setProvince(province);
        member.setCity(city);
        member.setArea(area);
        return this.update(member);
    }


    /**
     * 微信授权登录
     * @param query     请求参数
     * @return          返回用户信息
     */
    public NutMap wxLogin(QueryBody query){
        log.warn(Json.toJson(query));
        // 登录时获取的 code
        String code = query.getString("code");
        // 昵称/头像/性别/省份/城市
        String nickname = query.getString("nickname");
        String avatar = query.getString("avatar");
        int sex = query.getInt("sex");
        String province = query.getString("province");
        String city = query.getString("city");
        // 手机号凭证
        String mobileCode = query.getString("mobileCode");
        // 获取手机号:根据凭证到微信服务器
        String mobile = Wxs.getPhoneNumber(mobileCode);

        // 检查:获得微信临时登录凭证 code
        if(Strings.isBlank(code)){
            throw new BizException("用户登录凭证为空");
        }

        // 微信:登录凭证校验
        NutMap map = Wxs.jscode2session(code);
        String openid = map.getString("openid");
        String session_key = map.getString("session_key");
        if(Strings.isBlank(openid)){
            // 打印异常数据
            log.error("微信授权登录 -> 获取用户OPENID失败,数据:\n{}", Json.toJson(map));
            throw new BizException("获取用户openid失败,请检查小程序配置APPID,AppSecret是否正确");
        }

        // 通过openid获取用户
        Member member = this.fetch(Cnd.where("openid","=", openid));
        log.debug("打印昵称：{}, openid:{}" , nickname, openid);
        //nickname = Strings.sNull(EmojiParser.parseToAliases(nickname, EmojiParser.FitzpatrickAction.REMOVE));

        if(!Lang.isEmpty(member)){
            // 更新用户信息
            member.setSessionKey(session_key);
            member.setNickname(nickname);
            member.setAvatar(avatar);
            this.update(member);
        }else{
            // 注册新用户并启用该账号
            member = new Member();
            member.setOpenid(openid);
            member.setMobile(mobile);
            member.setSessionKey(session_key);
            member.setNickname(nickname);
            member.setAvatar(avatar);
            member.setSex(sex);
            member.setProvince(province);
            member.setCity(city);
            member.setStatus(true);
            this.create(member);
        }

        // 标记当前会话登录的账号id
        UserSecurityUtil.login(member.getId());
        UserSecurityUtil.checkLogin();

        // 组装返回数据
        NutMap nutMap = new NutMap();
        nutMap.addv("token", UserSecurityUtil.getTokenValue());
        nutMap.addv("userInfo",  member);
        return nutMap;
    }

}
