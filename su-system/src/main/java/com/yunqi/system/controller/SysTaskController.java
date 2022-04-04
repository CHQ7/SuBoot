package com.yunqi.system.controller;


import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.models.SysTask;
import com.yunqi.system.service.ISysTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by @author JsckChin on 2021/9/12
 */
@Slf4j
@RestController
@RequestMapping("/nt/sys/task")
public class SysTaskController {

    @Resource
    ISysTaskService sysTaskService;

    @PostMapping("/list")
    @RequiresPermissions("sys.task")
    public Result<?> list(Integer page, Integer pageSize, SysTask sysTask) {
        return Result.success().addData(sysTaskService.list(page, pageSize, sysTask));
    }


    @PostMapping("/create")
    @RequiresPermissions("sys.task.create")
    @SLog(tag = "系统任务",  type = LogType.INSERT)
    public Result<?> create(@Validated SysTask task) {
        sysTaskService.create(task);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.task")
    public Result<?> fetch(@PathVariable("id") String id) {
        sysTaskService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @RequiresPermissions("sys.task.update")
    @SLog(tag = "系统任务",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysTask task) {
        sysTaskService.update(task);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.task.delete")
    @SLog(tag = "系统任务",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysTaskService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/run/{id}")
    @RequiresPermissions("sys.task.run")
    @SLog(tag = "系统任务",  type = LogType.EXECUTE)
    public Result<?> run(@PathVariable("id") String id) {
        sysTaskService.run(id);
        return Result.success();
    }

}
