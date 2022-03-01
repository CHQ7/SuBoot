package com.yunqi.system.controller;


import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.security.annotation.RequiresAuthentication;
import com.yunqi.starter.security.spi.StpUtil;
import com.yunqi.starter.security.utils.SecurityUtil;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.SysMenuService;
import com.yunqi.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户认证
 * Created by @author JsckChin on 2021/9/21
 */
@Slf4j
@RestController
@RequestMapping("/nt/auth")
public class SysAuthController {

    @Resource
    SysUserService sysUserService;
    @Resource
    SysMenuService sysMenuService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        SysUser user = sysUserService.loginByPassword(username, password);
        // 登录并检查当前会话是否已经登录
        StpUtil.login(user.getId());
        StpUtil.checkLogin();

        // 记录用户登录信息
        sysUserService.loginInfo(user);

        // 设置session
        SecurityUtil.setUserName(user.getName());
        SecurityUtil.setUserNickname(user.getNickname());

        NutMap map = new NutMap();
        map.addv("token", StpUtil.getTokenValue());
        map.addv("user", user);
        map.addv("menus", sysMenuService.all());
        map.addv("avatar", user.getAvatar());
        map.addv("nickname", user.getNickname());
        map.addv("roles", sysUserService.getRoleList(user.getId()));
        return Result.success().addData(map);
    }


    @PostMapping("/info")
    @RequiresAuthentication
    public Object info() {
        SysUser user = sysUserService.fetch(SecurityUtil.getUserId());
        NutMap map = new NutMap();
        map.addv("avatar", user.getAvatar());
        map.addv("menus", sysMenuService.all());
        map.addv("nickname", user.getNickname());
        map.addv("roles", sysUserService.getRoleList(user.getId()));
        return Result.success().addData(map);
    }

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return            操作结果
     */
    @PostMapping("/updatePwd")
    @RequiresAuthentication
    public Object updatePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        sysUserService.updatePwd(oldPassword,newPassword);
        return Result.success();
    }

    /**
     * 用户退出登录
     * @return
     */
    @PostMapping("/logout")
    @RequiresAuthentication
    public Object logout() {
        // 获取会话ID
        String userId = SecurityUtil.getUserId();
        StpUtil.logout(userId);
        // 记录用户退出信息
        sysUserService.logoutInfo(userId);
        return Result.success();
    }


}
