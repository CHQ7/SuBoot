package com.yunqi.system.controller;


import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.security.annotation.RequiresAuthentication;
import com.yunqi.starter.security.spi.StpUtil;
import com.yunqi.starter.security.utils.SecurityUtil;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.ISysMenuService;
import com.yunqi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户认证
 * Created by @author CHQ on 2022/6/15
 */
@Slf4j
@RestController
@RequestMapping("/nt/auth")
public class SysAuthController {

    @Resource
    ISysUserService sysUserService;
    @Resource
    ISysMenuService sysMenuService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        SysUser user = sysUserService.loginByPassword(username, password);
        // 登录并检查当前会话是否已经登录
        StpUtil.login(user.getId());
        StpUtil.checkLogin();

        // 记录用户登录信息
        sysUserService.loginInfo(user);

        // 设置session
        SecurityUtil.setUserName(user.getUsername());
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
    public Result info() {
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
    public Result updatePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        sysUserService.updatePwd(oldPassword,newPassword);
        return Result.success();
    }

    /**
     * 用户退出登录
     * @return
     */
    @PostMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        // 获取会话ID
        String userId = SecurityUtil.getUserId();
        StpUtil.logout(userId);
        // 记录用户退出信息
        sysUserService.logoutInfo(userId);
        return Result.success();
    }


}
