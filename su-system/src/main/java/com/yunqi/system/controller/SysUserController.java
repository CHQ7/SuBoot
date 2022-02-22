package com.yunqi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.SysDeptService;
import com.yunqi.system.service.SysRoleService;
import com.yunqi.system.service.SysUserService;
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
@RequestMapping("/nt/sys/user")
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @Resource
    SysDeptService sysDeptService;

    @Resource
    SysRoleService sysRoleService;

    @PostMapping("/list")
    @SaCheckPermission("sys.user")
    public Object list(Integer pageNumber, Integer pageSize, SysUser user) {
        return Result.success().addData(sysUserService.list(pageNumber,pageSize,user));
    }

    @PostMapping("/create")
    @SaCheckPermission("sys.user.create")
    @SLog(tag = "系统用户",  type = LogType.INSERT)
    public Result<?> create(@Validated SysUser user, String roleIds) {
        sysUserService.create(user, roleIds.split(","));
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @SaCheckPermission("sys.user")
    public Result<?> fetch(@PathVariable("id") String id) {
        sysUserService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @SaCheckPermission("sys.user.update")
    @SLog(tag = "系统用户",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysUser user, String roleIds) {
        sysUserService.update(user, roleIds.split(","));
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys.user.delete")
    @SLog(tag = "系统用户",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysUserService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/resetPassword/{id}")
    @SaCheckPermission("sys.user.password")
    @SLog(tag = "系统用户",  type = LogType.UPDATE)
    public Result<?> resetPassword(@PathVariable("id") String id) {
        return Result.success().addData(sysUserService.resetPwd(id));
    }

    @PostMapping("/dept")
    @SaCheckPermission("sys.user")
    public Result<?> dept() {
        return Result.success().addData(sysDeptService.all());
    }

    @PostMapping("/role")
    @SaCheckPermission("sys.user")
    public Result<?> role() {
        return Result.success().addData(sysRoleService.all());
    }

}
