package com.yunqi.member.controller;

import com.yunqi.member.models.MemberLevel;
import com.yunqi.member.service.IMemberLevelService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户等级
 * Created by @author CHQ on 2022/10/7
 */
@RestController
@RequestMapping("/nt/member/level")
public class MemberLevelController {

    @Resource
    IMemberLevelService memberLevelService;

    @PostMapping("/list")
    @RequiresPermissions("member.level")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(memberLevelService.list(query));
    }

    @PostMapping("/create")
    @RequiresPermissions("member.level.create")
    @SLog(tag = "用户等级",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody MemberLevel level) {
        memberLevelService.create(level);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("member.level")
    public Result fetch(@PathVariable("id") String id) {
        memberLevelService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("member.level.update")
    @SLog(tag = "用户等级",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody MemberLevel level) {
        memberLevelService.update(level);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("member.level.delete")
    @SLog(tag = "用户等级",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") int id) {
        memberLevelService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("member.level.delete")
    @SLog(tag = "用户等级",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        memberLevelService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

}
