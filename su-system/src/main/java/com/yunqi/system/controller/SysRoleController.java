package com.yunqi.system.controller;

import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.models.SysMenu;
import com.yunqi.system.models.SysRole;
import com.yunqi.system.service.ISysMenuService;
import com.yunqi.system.service.ISysRoleService;
import org.nutz.lang.util.NutMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by @author CHQ on 2022/6/15
 */
@RestController
@RequestMapping("/nt/sys/role")
public class SysRoleController {

    @Resource
    ISysRoleService sysRoleService;

    @Resource
    ISysMenuService sysMenuService;

    @PostMapping("/list")
    @RequiresPermissions("sys.role")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(sysRoleService.list(query));
    }


    @PostMapping("/create")
    @RequiresPermissions("sys.role.create")
    @SLog(tag = "系统模块-系统角色", type = LogType.INSERT)
    public Result create(@Validated @RequestBody SysRole role) {
        sysRoleService.create(role);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.role")
    public Result fetch(@PathVariable("id") String id) {
        sysRoleService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @RequiresPermissions("sys.role.update")
    @SLog(tag = "系统模块-系统角色",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody SysRole role) {
        sysRoleService.update(role);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.role.delete")
    @SLog(tag = "系统模块-系统角色",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        sysRoleService.deleteById(id);
        return Result.success();
    }


    @PostMapping("/menu/{id}")
    @RequiresPermissions("sys.role")
    public Result menu(@PathVariable("id") String id) {
        List<SysMenu> hasList = sysMenuService.getMenusAndButtons(id);

        List<String> cmenu = new ArrayList<>();
        for (SysMenu menu : hasList) {
            cmenu.add(menu.getId());
        }
        NutMap map = new NutMap().addv("menu", sysMenuService.all()).addv("cmenu", cmenu);
        return Result.success().addData(map);
    }

    @PostMapping("/doMenu")
    @RequiresPermissions("sys.role.menu")
    public Result doMenu(@RequestBody QueryBody query) {
        sysRoleService.saveMenu(query.getString("menuIds").split(","), query.getString("roleId"));
        return Result.success();
    }

}
