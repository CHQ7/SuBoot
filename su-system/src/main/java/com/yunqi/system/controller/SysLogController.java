package com.yunqi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yunqi.starter.common.result.Result;
import com.yunqi.system.models.SysLog;
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
    @SaCheckPermission("sys.log")
    public Result<?> list(Integer pageNumber, Integer pageSize, String beginTime, String endTime, SysLog sysLog) {
        return Result.success().addData(sysLogService.list(pageNumber, pageSize, beginTime, endTime, sysLog));
    }

}
