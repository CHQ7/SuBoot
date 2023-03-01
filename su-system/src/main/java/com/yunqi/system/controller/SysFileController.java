package com.yunqi.system.controller;

import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import com.yunqi.starter.log.annotation.SLog;
import com.yunqi.starter.log.enums.LogType;
import com.yunqi.starter.security.annotation.RequiresPermissions;
import com.yunqi.system.service.ISysFileService;
import org.nutz.lang.Encoding;
import org.nutz.lang.Streams;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by @author CHQ on 2022/12/18
 */
@RestController
@RequestMapping("/nt/sys/file")
public class SysFileController {

    @Resource
    ISysFileService sysFileService;

    @PostMapping("/list")
    @RequiresPermissions("sys.file")
    public Result list(@RequestBody QueryBody query) {
        return Result.success().addData(sysFileService.list(query));
    }

    @PostMapping("/fetch/{id}")
    @RequiresPermissions("sys.file")
    public Result fetch(@PathVariable("id") String id) {
        return Result.success().addData(sysFileService.fetch(id));
    }

    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys.file.delete")
    @SLog(tag = "系统模块-系统文件",  type = LogType.DELETE)
    public Result delete(@PathVariable("id") String id) {
        sysFileService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresPermissions("sys.file.delete")
    @SLog(tag = "系统模块-系统文件",  type = LogType.DEL)
    public Result delete(@RequestBody QueryBody query) {
        sysFileService.deleteByIds(query.getString("ids"));
        return Result.success();
    }

    @RequestMapping("/download/{id}")
    @RequiresPermissions("sys.file.download")
    @SLog(tag = "系统模块-系统文件",  type = LogType.IMPORT)
    public void download(@PathVariable("id") String id, HttpServletResponse resp) throws IOException {
        File file = sysFileService.download(id);
        // 下载文件
        String filename = URLEncoder.encode(file.getName(), Encoding.UTF8);
        resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        resp.setHeader("Content-Length", "" + file.length());
        Streams.writeAndClose(resp.getOutputStream(), Streams.fileIn(file));
    }
}
