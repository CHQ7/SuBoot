package com.yunqi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 * Created by @author CHQ on 2022/1/21
 */
@EnableAsync
@SpringBootApplication
public class SuBootApplication {

    public static void main(String[] args) {
        SpringApplication app = new  SpringApplication(SuBootApplication.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
    }
}
