package com.yunqi.member.controller;

import com.yunqi.member.models.Member;
import com.yunqi.member.service.IMemberService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理
 * Created by @author CHQ on 2022/11/27
 */
@RestController
@RequestMapping("/nt/member")
public class MemberController {

    @Resource
    IMemberService memberService;


    @PostMapping("/list")
    @RequiresPermissions("member.list")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(memberService.list(query));
    }


    @PostMapping("/fetch/{id}")
    @RequiresPermissions("member.list")
    public Result fetch(@PathVariable("id") String id) {
        memberService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("member.list.update")
    @SLog(tag = "用户管理",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody Member member) {
        memberService.update(member);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("member.list.delete")
    @SLog(tag = "用户管理",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        memberService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("member.list.delete")
    @SLog(tag = "用户管理",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        memberService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

}
