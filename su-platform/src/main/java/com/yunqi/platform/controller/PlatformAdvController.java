package com.yunqi.platform.controller;

import com.yunqi.platform.models.PlatformAdv;
import com.yunqi.platform.service.IPlatformAdvService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 幻灯片
 * Created by @author CHQ on 2022/10/7
 */
@RestController
@RequestMapping("/nt/platform/adv")
public class PlatformAdvController {

    @Resource
    IPlatformAdvService platformAdvService;

    @PostMapping("/list")
    @RequiresPermissions("platform.adv")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(platformAdvService.list(query));
    }

    @PostMapping("/create")
    @RequiresPermissions("platform.adv.create")
    @SLog(tag = "平台幻灯片",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody PlatformAdv adv) {
        platformAdvService.create(adv);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("platform.adv")
    public Result fetch(@PathVariable("id") String id) {
        platformAdvService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("platform.adv.update")
    @SLog(tag = "平台幻灯片",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody PlatformAdv adv) {
        platformAdvService.update(adv);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("platform.adv.delete")
    @SLog(tag = "平台幻灯片",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        platformAdvService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("platform.adv.delete")
    @SLog(tag = "平台幻灯片",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        platformAdvService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

}
