package com.yunqi.system.controller;


import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.security.annotation.RequiresAuthentication;
import com.yunqi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return Result.success().addData(sysUserService.loginByPassword(query.getString("username"), query.getString("password")));
    }


    @PostMapping("/info")
    @RequiresAuthentication
    public Result info() {
        return Result.success().addData(sysUserService.userInfo());
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
     * @return              操作结果
     */
    @PostMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        sysUserService.logout();
        return Result.success();
    }


}
