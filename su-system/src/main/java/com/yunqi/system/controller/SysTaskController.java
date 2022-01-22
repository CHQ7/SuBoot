package com.yunqi.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.common.annotation.Slog;
import com.yunqi.common.base.enums.LogType;
import com.yunqi.common.base.result.Result;
import com.yunqi.system.models.SysTask;
import com.yunqi.system.service.SysTaskService;
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
    SysTaskService sysTaskService;

    @PostMapping("/list")
    @SaCheckPermission("sys.task")
    public Result<?> list(Integer pageNumber, Integer pageSize, SysTask sysTask) {
        return Result.success().addData(sysTaskService.list(pageNumber, pageSize, sysTask));
    }


    @PostMapping("/create")
    @SaCheckPermission("sys.task.create")
    @Slog(title = "系统任务",  type = LogType.INSERT)
    public Result<?> create(@Validated SysTask task) {
        sysTaskService.create(task);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @SaCheckPermission("sys.task")
    public Result<?> fetch(@PathVariable("id") String id) {
        sysTaskService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @SaCheckPermission("sys.task.update")
    @Slog(title = "系统任务",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysTask task) {
        sysTaskService.update(task);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys.task.delete")
    @Slog(title = "系统任务",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysTaskService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/run/{id}")
    @SaCheckPermission("sys.task.run")
    @Slog(title = "系统任务",  type = LogType.EXECUTE)
    public Result<?> run(@PathVariable("id") String id) {
        sysTaskService.run(id);
        return Result.success();
    }

}
