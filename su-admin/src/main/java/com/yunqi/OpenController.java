package com.yunqi;

import com.yunqi.starter.common.result.Result;
import org.nutz.lang.util.NutMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author JsckChin on 2021/9/21
 */
@RestController
@RequestMapping("/api")
public class OpenController {


    @GetMapping("/")
    public Object tet() {
        NutMap map = new NutMap();
        map.addv("token", "3333");
        map.addv("user", "9999");
        return Result.error("222");
    }


}
