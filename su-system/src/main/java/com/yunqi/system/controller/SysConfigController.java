package com.yunqi.system.controller;

import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.models.SysConfig;
import com.yunqi.system.service.ISysConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by @author CHQ on 2022/6/15
 */
@RestController
@RequestMapping("/nt/sys/conf")
public class SysConfigController {

    @Resource
    ISysConfigService sysConfigService;

    @PostMapping("/list")
    @RequiresPermissions("sys.conf")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(sysConfigService.list(query));
    }


    @PostMapping("/create")
    @RequiresPermissions("sys.conf.create")
    @SLog(tag = "系统模块-系统参数",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody SysConfig conf) {
        sysConfigService.create(conf);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.conf")
    public Result fetch(@PathVariable("id") String id) {
        sysConfigService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @RequiresPermissions("sys.conf.update")
    @SLog(tag = "系统模块-系统参数",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody SysConfig conf) {
        sysConfigService.update(conf);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.conf.delete")
    @SLog(tag = "系统模块-系统参数",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        sysConfigService.deleteById(id);
        return Result.success();
    }

}
