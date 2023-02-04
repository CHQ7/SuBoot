package com.yunqi;

import com.yunqi.member.service.IMemberLevelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * Created by @author JsckChin on 2022/1/30
 */
@Slf4j
@SpringBootTest
class Tests {

    @Resource
    IMemberLevelService memberLevelService;

    @Test
    public void test() {

    }


}
