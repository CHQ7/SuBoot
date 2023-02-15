package com.yunqi.api;

import com.yunqi.member.service.IMemberService;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.security.annotation.Authentication;
import com.yunqi.starter.security.spi.UserSecurityUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户接口
 * Created by @author CHQ on 2022/11/3
 */
@Authentication
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    @Resource
    IMemberService memberService;

    /**
     * 获取用户信息
     * @return  统一数据返回格式
     */
    @PostMapping("/userInfo")
    public Result userInfo() {
        return Result.success().addData(memberService.userInfo(Strings.sNull(UserSecurityUtil.getLoginId())));
    }


    /**
     * 修改用户昵称
     * @param query 请求数据
     * @return      统一数据返回格式
     */
    @PostMapping("/nickname")
    public Result nickname(@RequestBody QueryBody query) {
        if(Strings.isBlank(query.getString("nickname"))){
            return Result.error("用户名不能为空");
        }
        memberService.updateNickname(Strings.sNull(UserSecurityUtil.getLoginId()), query.getString("nickname"));
        return Result.success();
    }

    /**
     * 修改用户性别
     * @param query 请求数据
     * @return      统一数据返回格式
     */
    @PostMapping("/sex")
    public Result sex(@RequestBody QueryBody query) {
        if(Strings.isBlank(query.getString("sex"))){
            return Result.error("性别不能为空");
        }
        memberService.updateSex(Strings.sNull(UserSecurityUtil.getLoginId()), query.getInt("sex"));
        return Result.success();
    }

    /**
     * 修改用户生日
     * @param query 请求数据
     * @return      统一数据返回格式
     */
    @PostMapping("/birthday")
    public Result birthday(@RequestBody QueryBody query) {
        if(Strings.isBlank(query.getString("birthday"))){
            return Result.error("生日不能为空");
        }
        memberService.updateBirthday(Strings.sNull(UserSecurityUtil.getLoginId()), query.getString("birthday"));
        return Result.success();
    }

    /**
     * 修改用户地区
     * @param query 请求数据
     * @return      统一数据返回格式
     */
    @PostMapping("/region")
    public Result region(@RequestBody QueryBody query) {
        if(Strings.isBlank(query.getString("province")) ||
                Strings.isBlank(query.getString("city")) ||
                Strings.isBlank(query.getString("area"))){
            return Result.error("地区不能为空");
        }
        memberService.updateRegion(Strings.sNull(UserSecurityUtil.getLoginId()), query.getString("province"), query.getString("city"), query.getString("area"));
        return Result.success();
    }

}
