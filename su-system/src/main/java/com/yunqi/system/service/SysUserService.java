package com.yunqi.system.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.lang.mvc.Mvcs;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.security.utils.SecurityUtil;
import com.yunqi.system.models.SysAuthLog;
import com.yunqi.system.models.SysRole;
import com.yunqi.system.models.SysUser;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author JsckChin on 2021/10/1
 */
@Service
public class SysUserService extends BaseServiceImpl<SysUser> {

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysAuthLogService sysAuthLogService;

    /**
     * 用户列表
     * @param pageNumber 页码
     * @param pageSize   每页几条数据
     * @param user       name:用户账号,nickname:用户姓名
     * @return           分页列表
     */
    public Object list(Integer pageNumber, int pageSize, SysUser user) {
        logger.warn("查询用户列表");
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:用户账号
        if(Strings.isNotBlank(user.getName())){
            cnd.and("name", "like", "%" + user.getName() + "%");
        }
        // 模糊查询:用户姓名
        if(Strings.isNotBlank(user.getNickname())){
            cnd.and("nickname", "like", "%" + user.getNickname() + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPageLinks(pageNumber, pageSize, cnd,"^(dept|roles)$");
    }



    /**
     * 创建用户
     * @param user 用户
     */
    @Transactional
    public void create(SysUser user,  String[] roleIds) {
        // 检查:账号是否存在
        if (this.count(Cnd.where("name","=", user.getName())) > 0) {
            throw new BizException("账号已存在");
        }
        // 选填:用户手机号
        if (Strings.isNotBlank(user.getMobile())) {
            if (this.count(Cnd.where("mobile","=", user.getMobile())) > 0) {
                throw new BizException("手机号已存在");
            }
        }
        // 选填:用户邮箱
        if (Strings.isNotBlank(user.getEmail())) {
            if (this.count(Cnd.where("email","=", user.getEmail())) > 0) {
                throw new BizException("该邮箱已存在");
            }
        }
        // -----------------------------
        // 建立用户档案
        // -----------------------------
        String salt = R.UU32(); // 密码盐
        String hashPassword = hashPassword(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(hashPassword);
        // 初始化登录次数
        user.setLoginCount(0);
        this.insert(user);
        // 创建用户关联角色
        if (roleIds != null) {
            for (String roleId : roleIds) {
                this.dao().insert("ims_sys_user_role", Chain.from(NutMap.NEW().addv("roleId", roleId).addv("userId", user.getId())));
            }
        }
    }

    /**
     * 更新用户
     * @param user 用户
     */
    @Transactional
    public void update(SysUser user, String[] roleIds) {
        SysUser oldUser = this.fetch(user.getId());
        if(oldUser == null){
            throw new BizException("未知用户");
        }
        // 超级管理员禁止操作
        if(oldUser.getName().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_NAME)){
            throw new BizException("超级管理员禁止操作");
        }
        // 检查:账号是否存在
        if(!Strings.sBlank(oldUser.getName()).equalsIgnoreCase(user.getName())) {
            if (this.count(Cnd.where("name","=", user.getName())) > 0) {
                throw new BizException("账号已存在");
            }
        }
        // -----------------------------
        // 更新用户档案
        // -----------------------------
        user.setPassword(null);
        user.setSalt(null);
        this.updateIgnoreNull(user);
        // 清除该管理员对应角色
        this.dao().clear("ims_sys_user_role", Cnd.where("userId", "=", user.getId()));
        // 为管理员添加对应角色
        for (String roleId : roleIds) {
            this.dao().insert("ims_sys_user_role", Chain.from(NutMap.NEW().addv("roleId", roleId).addv("userId", user.getId())));
        }
    }

    /**
     * 删除用户并且删除用户相关依赖
     * @param id 用户ID
     */
    @Transactional
    public void deleteById(String id) {
        SysUser user = this.fetch(id);
        // 超级管理员禁止操作
        if(user != null && user.getName().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_NAME)){
            throw new BizException("超级管理员禁止操作");
        }
        // 删除用户对应部门相关依赖
        dao().clear("ims_sys_user_dept", Cnd.where("userId", "=", id));
        // 删除用户对应角色相关依赖
        dao().clear("ims_sys_user_role", Cnd.where("userId", "=", id));
        // 删除用户
        dao().clear("ims_sys_user", Cnd.where("id", "=", id));
    }

    /**
     * 获取用户角色
     * @param userId 用户ID
     * @return       用户角色
     */
    public List<String> getRoleList(String userId) {
        // 根据用户ID查询用户
        SysUser user = this.fetch(userId);
        if (user == null) {
            return new ArrayList<>();
        }
        // 根据用户查询角色
        this.fetchLinks(user, "roles");
        if (user.getRoles() == null) {
            return new ArrayList<>();
        }

        List<String> roleList = new ArrayList<>();
        for (SysRole role : user.getRoles()) {
            // 检查角色是否被禁用
            if (role.isDisabled()) {
                roleList.add(role.getCode());
            }
        }
        // TODO 追加公共角色权限
        return roleList;
    }

    /**
     * 获取用户权限
     * @param userId    用户ID
     * @return          用户权限
     */
    public List<String> getPermissionList(String userId) {
        // 根据用户ID查询用户
        SysUser user = this.fetch(userId);
        if (user == null) {
            return new ArrayList<>();
        }
        // 根据用户查询角色
        this.fetchLinks(user, "roles");
        if (user.getRoles() == null) {
            return new ArrayList<>();
        }
        List<String> permissionList = new ArrayList<>();
        for (SysRole role : user.getRoles()) {
            // 检查角色是否被禁用
            if (role.isDisabled()) {
                permissionList.addAll(sysRoleService.getPermissionList(role));
            }
        }

        // TODO 追加公共角色权限
        return permissionList;
    }


    /**
     * 账号密码登录
     * @param loginname     账号
     * @param passowrd      密码
     * @return              用户
     */
    public SysUser loginByPassword(String loginname, String passowrd) {
        SysUser user = this.fetch(Cnd.where("name", "=", loginname));
        if (user == null) {
            throw new BizException("账号不存在");
        }
        String hashedPassword = hashPassword(passowrd, user.getSalt());
        System.out.println("密码:" + hashedPassword);
        logger.debug("登录密码：" + hashedPassword + " 对比密码" +  user.getPassword());
        if (!Strings.sNull(hashedPassword).equalsIgnoreCase(user.getPassword())) {
            throw new BizException("账号密码不正确");
        }
        if (!user.isDisabled()) {
            throw new BizException("账号被禁用");
        }
        return user;
    }

    /**
     * 记录用户登录信息
     * @param user  用户ID
     */
    public void loginInfo(SysUser user){
        // *========获取请求=========*
        HttpServletRequest req  = Mvcs.getReq();
        // 获取终端信息
        final UserAgent ua = UserAgentUtil.parse(req.getHeader("User-Agent"));
        // 最近登录时间
        user.setLoginAt(System.currentTimeMillis());
        // 累计登录次数
        user.setLoginCount(user.getLoginCount() + 1);
        // 最近登录IP
        user.setLoginIp(Lang.getIP(req));
        // 获取客户端
        user.setLoginBrowser(ua.getBrowser().getName() + "_" + ua.getVersion());
        // 获取操作系统
        user.setLoginOs(ua.getPlatform().getName() + "_"  + ua.getOsVersion());
        // 标记登录状态
        user.setOnline(true);
        this.update(user);
        // *========异步记录数据库日志=========*
        SysAuthLog log = new SysAuthLog();
        log.setTag("用户登陆");
        log.setMsg("登录系统");
        log.setName(user.getNickname());
        sysAuthLogService.saveLog(req, log);
    }

    /**
     * 记录用户登出信息
     * @param userId 用户ID
     */
    public void logoutInfo(String userId){
        // *========获取请求=========*
        HttpServletRequest req  = Mvcs.getReq();
        SysUser user = this.fetch(userId);
        // 标记退出状态
        user.setOnline(false);
        this.update(user);
        // *========异步记录数据库日志=========*
        SysAuthLog log = new SysAuthLog();
        log.setTag("用户登出");
        log.setMsg("退出系统");
        log.setName(user.getNickname());
        sysAuthLogService.saveLog(req, log);
    }


    /**
     * 重置密码(随机6位数密码)
     * @param userId    用户ID
     * @return          明文密码
     */
    public String resetPwd(String userId) {
        String password = RandomUtil.randomNumbers(6);
        return this.resetPwd(userId, password);
    }


    /**
     * 重置密码
     * @param userId    用户ID
     * @param password  明文密码
     * @return          明文密码
     */
    @Transactional
    public String resetPwd(String userId, String password) {
        SysUser user = this.fetch(userId);
        // 只能超级管理员自己操作
        if(!SecurityUtil.getUserName().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_NAME)){
            if(user != null && user.getName().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_NAME)){
                throw new BizException("超级管理员禁止操作");
            }
        }
        String salt = R.UU32();
        this.update(Chain.make("password", hashPassword(password,salt)).add("salt", salt), Cnd.where("id", "=", userId));
        return password;
    }

    public void updatePwd(String oldPwd, String newPwd){
        if(!(newPwd.length() >= 6 && newPwd.length()<= 20)){
            throw new BizException("新密码长度必须是6-20位");
        }
        // 获取当前登录的用户ID
        SysUser user = this.fetch(SecurityUtil.getUserId());
        String old = this.hashPassword(oldPwd, user.getSalt());
        if(!old.equals(user.getPassword())){
            throw new BizException("密码不正确");
        }
        // 更新密码
        this.resetPwd(user.getId(), newPwd);
    }


    /**
     * 哈希密码
     * @param password  密码
     * @param salt      密码盐
     * @return          数字签名
     */
    public String hashPassword(String password,String salt){
        return Lang.sha256BySalt(password, salt);
    }

}
