package com.yunqi.common.config.nutz;

import org.nutz.dao.Dao;
import org.nutz.filepool.FilePool;
import org.nutz.filepool.NutFilePool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     Nutz配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Configuration
@ConditionalOnBean({Dao.class})
public class NutzConfiguration {

    @Bean
    public SpringResourceLoaction springResourceLoaction() {
        return new SpringResourceLoaction();
    }

    @Bean
    public FilePool filePool() {
        // 使用相对路径 临时文件最大个数为 1000 个
        return new NutFilePool(".temp", 0);
    }
}
