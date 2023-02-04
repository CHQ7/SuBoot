package com.yunqi.platform.controller;

import com.yunqi.platform.models.PlatformNav;
import com.yunqi.platform.service.IPlatformNavService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 导航列表
 * Created by @author CHQ on 2022/10/7
 */
@RestController
@RequestMapping("/nt/platform/nav")
public class PlatformNavController {

    @Resource
    IPlatformNavService platformNavService;

    @PostMapping("/list")
    @RequiresPermissions("platform.nav")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(platformNavService.list(query));
    }

    @PostMapping("/create")
    @RequiresPermissions("platform.nav.create")
    @SLog(tag = "平台导航",  type = LogType.INSERT)
    public Result create(@Validated @RequestBody PlatformNav nav) {
        platformNavService.create(nav);
        return Result.success();
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("platform.nav")
    public Result fetch(@PathVariable("id") String id) {
        platformNavService.fetch(id);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("platform.nav.update")
    @SLog(tag = "平台导航",  type = LogType.UPDATE)
    public Result update(@Validated @RequestBody PlatformNav nav) {
        platformNavService.update(nav);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("platform.nav.delete")
    @SLog(tag = "平台导航",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        platformNavService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("platform.nav.delete")
    @SLog(tag = "平台导航",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        platformNavService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

}
