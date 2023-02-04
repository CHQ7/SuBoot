package com.yunqi.member.service;

import com.yunqi.member.models.MemberLevel;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * 用户等级
 * Created by @author CHQ on 2022/11/2
 */
@Service
public class IMemberLevelService extends BaseServiceImpl<MemberLevel> {

    /**
     * 等级列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<MemberLevel> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:等级名字
        if(Strings.isNotBlank(query.getString("name"))){
            cnd.and("name", "like", "%" + query.getString("name") + "%");
        }
        // 精确查询:状态
        if(Strings.isNotBlank(query.getString("status"))){
            cnd.and("status", "=", query.getBoolean("status"));
        }
        // 等级权重排序
        cnd.asc("id");
        return this.listPage(query.page(),query.pageSize(), cnd);
    }

    /**
     * 全部等级
     * @return      等级数据,按照等级权重排序
     */
    public List<MemberLevel> all() {
        return this.query(Cnd.where("status","=",true).asc("id"));
    }

    /**
     * 获取等级
     * @param id    等级ID
     * @return      等级
     */
    public MemberLevel fetchById(int id){
        return this.fetch(id);
    }


    /**
     * 创建等级
     * @param level   新的等级
     * @return        等级
     */
    @Transactional
    public MemberLevel create(MemberLevel level) {
        // 检查等级权重
        if (countById(level.getId()) > 0) {
            throw new BizException("等级权重已存在");
        }
        return this.insert(level);
    }

    /**
     * 统计等级权重
     * @param id 等级权重
     * @return      统计数量
     */
    private int countById(int id){
        return this.count(Cnd.where("id","=", id));
    }


    /**
     * 统计标题
     * @param title 等级标题
     * @return      统计数量
     */
    private int countByTitle(String title){
        return this.count(Cnd.where("title","=", title));
    }

    /**
     * 更新等级
     * @param level  更新等级
     * @return       boolean
     */
    @Transactional
    public boolean update(MemberLevel level) {
        MemberLevel oldLevel = this.fetch(level.getId());
        // 检查等级权重
        if(!( oldLevel.getId() == level.getId() )) {
            if (countById(level.getId()) > 0) {
                throw new BizException("等级权重已存在");
            }
        }
        return this.updateIgnoreNull(level) != 0;
    }

    /**
     * 删除等级
     * @param id    ID
     * @return      boolean
     */
    @Transactional
    public boolean deleteById(int id) {
        if(id == 0){
            throw new BizException("默认等级,禁止删除");
        }
        return this.delete(id) != 0;
    }

    /**
     * 批量删除等级
     * @param ids   ID数组字符串
     */
    @Transactional
    public void deleteByIds(String ids) {
        if(Strings.isNotBlank(ids)){
            String[] arr =  ids.split(",");
            if(Arrays.asList(arr).contains("0")){
                throw new BizException("默认等级,禁止删除");
            }
            this.delete(arr);
        }
    }

}
