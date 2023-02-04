package com.yunqi.api;

import com.yunqi.platform.service.IPlatformAdvService;
import com.yunqi.platform.service.IPlatformNavService;
import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.common.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by @author CHQ on 2022/10/29
 */
@RestController
@RequestMapping("/api/platform")
public class OpenPlatformApiController {

    @Resource
    IPlatformAdvService platformAdvService;

    @Resource
    IPlatformNavService platformNavService;

    @RequestMapping("/index")
    public Result index() {
        NutMap map = new NutMap();
        // 幻灯片列表
        map.addv("adv", platformAdvService.all());
        // 导航列表
        map.addv("nav", platformNavService.all());
        return Result.success().addData(map);
    }

}
