package com.yunqi.system.controller;


import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.models.SysMenu;
import com.yunqi.system.service.ISysMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Created by @author CHQ on 2022/6/15
 */
@RestController
@RequestMapping("/nt/sys/menu")
public class SysMenuController {

    @Resource
    ISysMenuService sysMenuService;

    @PostMapping("/list")
    @RequiresPermissions("sys.menu")
    public Result list() {
        return Result.success().addData(sysMenuService.all());
    }


    @PostMapping("/create")
    @RequiresPermissions("sys.menu.create")
    @SLog(tag = "系统菜单",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody SysMenu menu) {
        sysMenuService.create(menu);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.menu")
    public Result fetch(@PathVariable("id") String id) {
        return Result.success().addData(sysMenuService.fetchById(id));
    }


    @PostMapping("/update")
    @RequiresPermissions("sys.menu.update")
    @SLog(tag = "系统菜单",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody SysMenu menu) {
        sysMenuService.update(menu);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.menu.delete")
    @SLog(tag = "系统菜单",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        sysMenuService.deleteAndChild(id);
        return Result.success();
    }

    @PostMapping("/sort")
    @RequiresPermissions("sys.menu.sort")
    @SLog(tag = "系统菜单",  type = LogType.SORT)
    public Result sort(@RequestBody QueryBody query){
        sysMenuService.sort(query.getString("ids"));
        return Result.success();
    }


    @PostMapping("/batch")
    @RequiresPermissions("sys.menu.batch")
    @SLog(tag = "系统菜单",  type = LogType.INSERT)
    public Result batch(@RequestBody QueryBody query){
        sysMenuService.batch(query.getString("parentId"), query.getList("permissionData", NutMap.class));
        return Result.success();
    }


}
