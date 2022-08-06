package com.yunqi.system.controller;


import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.security.annotation.RequiresAuthentication;
import com.yunqi.starter.security.spi.StpUtil;
import com.yunqi.starter.security.utils.SecurityUtil;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.ISysMenuService;
import com.yunqi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * 账号密码登录
     * @param query         请求参数
     * @return              统一数据返回格式
     * <br>
     * 请求参数：<br>
     * username             账号<br>
     * passowrd             密码
     */
    @PostMapping("/login")
    public Result login(@RequestBody QueryBody query) {
        SysUser user = sysUserService.loginByPassword(query.getString("username"), query.getString("password"));
        // 登录并检查当前会话是否已经登录
        StpUtil.login(user.getId());
        StpUtil.checkLogin();

        // 记录用户登录信息
        sysUserService.loginInfo(user);

        // 设置session
        SecurityUtil.setUserName(user.getUsername());
        SecurityUtil.setUserNickname(user.getNickname());

        return Result.success().addData(StpUtil.getTokenValue());
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
     * @param query         请求参数
     * @return              操作结果
     */
    @PostMapping("/updatePassword")
    @RequiresAuthentication
    public Result updatePassword(@RequestBody QueryBody query) {
        sysUserService.updatePassword(query.getString("oldPassword"),query.getString("newPassword"));
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
