package com.yunqi.system.controller;

import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.model.SysLog;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.service.SysLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by @author JsckChin on 2021/11/28
 */
@RestController
@RequestMapping("/nt/sys/log")
public class SysLogController {

    @Resource
    SysLogService sysLogService;

    @PostMapping("/list")
    @RequiresPermissions("sys.log")
    public Result<?> list(Integer pageNumber, Integer pageSize, String beginTime, String endTime, SysLog sysLog) {
        return Result.success().addData(sysLogService.list(pageNumber, pageSize, beginTime, endTime, sysLog));
    }

}
