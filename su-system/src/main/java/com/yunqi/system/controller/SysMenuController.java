package com.yunqi.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.common.annotation.Slog;
import com.yunqi.common.base.enums.LogType;
import com.yunqi.common.base.result.Result;
import com.yunqi.system.models.SysMenu;
import com.yunqi.system.service.SysMenuService;
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
@RequestMapping("/nt/sys/menu")
public class SysMenuController {

    @Resource
    SysMenuService sysMenuService;

    @PostMapping("/list")
    @SaCheckPermission("sys.menu")
    public Result<?> list() {
        return Result.success().addData(sysMenuService.all());
    }


    @PostMapping("/create")
    @SaCheckPermission("sys.menu.create")
    @Slog(title = "系统菜单",  type = LogType.INSERT)
    public Result<?> create(@Validated SysMenu menu) {
        sysMenuService.create(menu);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @SaCheckPermission("sys.menu")
    public Result<?> fetch(@PathVariable("id") String id) {
        return Result.success().addData(sysMenuService.fetchById(id));
    }


    @PostMapping("/update")
    @SaCheckPermission("sys.menu.update")
    @Slog(title = "系统菜单",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysMenu menu) {
        sysMenuService.update(menu);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys.menu.delete")
    @Slog(title = "系统菜单",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysMenuService.deleteAndChild(id);
        return Result.success();
    }

    @PostMapping("/sort")
    @SaCheckPermission("sys.menu.sort")
    @Slog(title = "系统菜单",  type = LogType.SORT)
    public Result<?> sort(String ids){
        sysMenuService.sort(ids);
        return Result.success();
    }

}
