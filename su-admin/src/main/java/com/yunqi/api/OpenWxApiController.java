package com.yunqi.api;

import com.yunqi.member.service.IMemberService;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by @author CHQ on 2022/11/2
 */
@RestController
@RequestMapping("/api/wx")
public class OpenWxApiController {

    @Resource
    IMemberService memberService;

    @PostMapping("/login")
    public Result login(@RequestBody QueryBody query) {
        return Result.success().addData(memberService.wxLogin(query));
    }

}
