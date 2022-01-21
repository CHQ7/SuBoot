package com.yunqi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Created by @author JsckChin on 2022/1/21
 */
@SpringBootApplication
public class SuBootApplication {

    public static void main(String[] args) {
        SpringApplication app = new  SpringApplication(SuBootApplication.class);
        app.setBannerMode(Banner.Mode.LOG); //将banner打印到日志文件
        app.run(args);
    }
}
