package com.yunqi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import com.yunqi.common.base.result.Result;
import com.yunqi.system.models.SysAuthLog;
import com.yunqi.system.service.SysAuthLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by @author JsckChin on 2021/11/28
 */
@RestController
@RequestMapping("/nt/sys/auth/log")
public class SysAuthLogController {

    @Resource
    SysAuthLogService sysAuthLogService;

    @PostMapping("/list")
    @SaCheckPermission("sys.auth.log")
    public Result<?> list(Integer pageNumber, Integer pageSize, String beginTime, String endTime, SysAuthLog sysAuthLog) {
        return Result.success().addData(sysAuthLogService.list(pageNumber, pageSize, beginTime, endTime, sysAuthLog));
    }

}
