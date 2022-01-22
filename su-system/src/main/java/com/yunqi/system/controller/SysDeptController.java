package com.yunqi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.common.annotation.Slog;
import com.yunqi.common.base.enums.LogType;
import com.yunqi.common.base.result.Result;
import com.yunqi.system.models.SysDept;
import com.yunqi.system.service.SysDeptService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by @author JsckChin on 2021/9/12
 */
@RestController
@RequestMapping("/nt/sys/dept")
public class SysDeptController {

    @Resource
    SysDeptService sysDeptService;

    @PostMapping("/list")
    @SaCheckPermission("sys.dept")
    public Result<?> list() {
        return Result.success().addData(sysDeptService.all());
    }


    @PostMapping("/create")
    @SaCheckPermission("sys.dept.create")
    @Slog(title = "系统组织",  type = LogType.INSERT)
    public Result<?> create(@Validated SysDept dept) {
        sysDeptService.create(dept);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @SaCheckPermission("sys.dept")
    public Result<?> fetch(@PathVariable("id") String id) {
        sysDeptService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @SaCheckPermission("sys.dept.update")
    @Slog(title = "系统组织",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysDept dept) {
        sysDeptService.update(dept);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys.dept.delete")
    @Slog(title = "系统组织",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysDeptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/sort")
    @SaCheckPermission("sys.dept.sort")
    @Slog(title = "系统组织",  type = LogType.SORT)
    public Result<?> sort(String ids){
        sysDeptService.sort(ids.split(","));
        return Result.success();
    }

}
