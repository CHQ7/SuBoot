package com.yunqi;

import com.yunqi.starter.common.lang.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;


/**
 * Created by @author JsckChin on 2022/1/30
 */
@SpringBootTest
class Tests {

    @Test
    public void test(){
        System.out.println(Strings.unicodeDecode("\\u5df2\\u89e3\\u5c01"));
    }

}
