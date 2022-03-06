package com.yunqi;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by @author JsckChin on 2022/1/30
 */
@SpringBootTest
class EmailUtilTest {

    @Resource
    StringEncryptor jasyptStringEncryptor;

    @Test
    public void test(){

        System.out.println(jasyptStringEncryptor.encrypt("root"));
    }

}
