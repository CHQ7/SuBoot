package com.yunqi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.common.annotation.Slog;
import com.yunqi.common.base.enums.LogType;
import com.yunqi.common.base.result.Result;
import com.yunqi.system.models.SysMenu;
import com.yunqi.system.models.SysRole;
import com.yunqi.system.service.SysMenuService;
import com.yunqi.system.service.SysRoleService;
import org.nutz.lang.util.NutMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by @author JsckChin on 2021/9/12
 */
@RestController
@RequestMapping("/nt/sys/role")
public class SysRoleController {

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysMenuService sysMenuService;

    @PostMapping("/list")
    @SaCheckPermission("sys.role")
    public Result<?> list(Integer pageNumber,Integer pageSize, SysRole role) {
        return Result.success().addData(sysRoleService.list(pageNumber, pageSize, role));
    }


    @PostMapping("/create")
    @SaCheckPermission("sys.role.create")
    @Slog(title = "系统角色",  type = LogType.INSERT)
    public Result<?> create(@Validated SysRole role) {
        sysRoleService.create(role);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @SaCheckPermission("sys.role")
    public Result<?> fetch(@PathVariable("id") String id) {
        sysRoleService.fetch(id);
        return Result.success();
    }


    @PostMapping("/update")
    @SaCheckPermission("sys.role.update")
    @Slog(title = "系统角色",  type = LogType.UPDATE)
    public Result<?> update(@Validated SysRole role) {
        sysRoleService.update(role);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys.role.delete")
    @Slog(title = "系统角色",  type = LogType.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        sysRoleService.deleteById(id);
        return Result.success();
    }


    @PostMapping("/menu/{id}")
    @SaCheckPermission("sys.role")
    public Result<?> menu(@PathVariable("id") String id) {
        List<SysMenu> hasList = sysMenuService.getMenusAndButtons(id);

        List<String> cmenu = new ArrayList<>();
        for (SysMenu menu : hasList) {
            cmenu.add(menu.getId());
        }
        NutMap map = new NutMap().addv("menu", sysMenuService.all()).addv("cmenu", cmenu);
        return Result.success().addData(map);
    }

    @PostMapping("/doMenu")
    @SaCheckPermission("sys.role.menu")
    public Result<?> doMenu(@RequestParam String menuIds, @RequestParam String roleId) {
        sysRoleService.saveMenu(menuIds.split(","), roleId);
        return Result.success();
    }

}
