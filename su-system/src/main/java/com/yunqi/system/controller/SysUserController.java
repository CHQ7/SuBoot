package com.yunqi.system.controller;

import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.ISysDeptService;
import com.yunqi.system.service.ISysRoleService;
import com.yunqi.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Created by @author CHQ on 2022/6/15
 */
@RestController
@RequestMapping("/nt/sys/user")
public class SysUserController {

    @Resource
    ISysUserService sysUserService;

    @Resource
    ISysDeptService sysDeptService;

    @Resource
    ISysRoleService sysRoleService;

    @PostMapping("/list")
    @RequiresPermissions("sys.user")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(sysUserService.list(query));
    }

    @PostMapping("/create")
    @RequiresPermissions("sys.user.create")
    @SLog(tag = "系统模块-系统用户",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody SysUser user) {
        sysUserService.create(user);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.user")
    public Result fetch(@PathVariable("id") String id) {
        sysUserService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("sys.user.update")
    @SLog(tag = "系统模块-系统用户",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody SysUser user) {
        sysUserService.update(user);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.user.delete")
    @SLog(tag = "系统模块-系统用户",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        sysUserService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/resetPassword/{id}")
    @RequiresPermissions("sys.user.password")
    @SLog(tag = "系统模块-系统用户",  type = LogType.UPDATE)
    public Result resetPassword(@PathVariable("id") String id) {
        return Result.success().addData(sysUserService.resetPassword(id));
    }

    @PostMapping("/dept")
    @RequiresPermissions("sys.user")
    public Result dept() {
        return Result.success().addData(sysDeptService.all());
    }

    @PostMapping("/role")
    @RequiresPermissions("sys.user")
    public Result role() {
        return Result.success().addData(sysRoleService.all());
    }

}
