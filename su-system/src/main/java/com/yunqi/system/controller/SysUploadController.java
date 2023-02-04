package com.yunqi.system.controller;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.result.Result;
import com.yunqi.system.service.ISysFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传
 * Created by @author CHQ on 2022/12/18
 */
@RestController
@RequestMapping("/nt/upload")
public class SysUploadController {

    @Resource
    ISysFileService sysFileService;


    @PostMapping("/image")
    public Result image(MultipartFile file) {
        String filePath = sysFileService.upLoadImage(file);
        if(Strings.isEmpty(filePath)){
            return Result.error("上传失败");
        }
        return Result.success().addData(filePath);
    }


    @PostMapping("/file")
    public Result file(MultipartFile file) {
        String filePath = sysFileService.upLoadFile(file);
        if(Strings.isEmpty(filePath)){
            return Result.error("上传失败");
        }
        return Result.success().addData(filePath);
    }

}
