package com.yunqi.member.controller;

import com.yunqi.member.service.IMemberLabelService;
import com.yunqi.member.service.IMemberLevelService;
import com.yunqi.starter.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员数据管理
 * Created by @author CHQ on 2022/11/3
 */
@RestController
@RequestMapping("/nt/member/data")
public class MemberDataController {


    @Resource
    IMemberLevelService memberLevelService;

    @Resource
    IMemberLabelService memberLabelService;

    /**
     * 用户等级
     * @return  统一返回格式
     */
    @PostMapping("/level")
    public Result level(){
        return Result.success().addData(memberLevelService.all());
    }

    /**
     * 用户标签
     * @return  统一返回格式
     */
    @PostMapping("/label")
    public Result label(){
        return Result.success().addData(memberLabelService.all());
    }


}
