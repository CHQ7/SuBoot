package com.yunqi.member.controller;

import com.yunqi.member.models.MemberLabel;
import com.yunqi.member.service.IMemberLabelService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户标签
 * Created by @author CHQ on 2022/10/7
 */
@RestController
@RequestMapping("/nt/member/label")
public class MemberLabelController {

    @Resource
    IMemberLabelService memberLabelService;

    @PostMapping("/list")
    @RequiresPermissions("member.label")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(memberLabelService.list(query));
    }

    @PostMapping("/create")
    @RequiresPermissions("member.label.create")
    @SLog(tag = "用户标签",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody MemberLabel label) {
        memberLabelService.create(label);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("member.label")
    public Result fetch(@PathVariable("id") String id) {
        memberLabelService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("member.label.update")
    @SLog(tag = "用户标签",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody MemberLabel label) {
        memberLabelService.update(label);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("member.label.delete")
    @SLog(tag = "用户标签",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        memberLabelService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("member.label.delete")
    @SLog(tag = "用户标签",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        memberLabelService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

}
