package com.yunqi.starter.redis.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by @author JsckChin on 2022/2/2
 */
@Configuration
@ConditionalOnExpression("${su.quartz.enabled:true}")
@EnableConfigurationProperties(RedisAutoConfigurationProperties.class)
@AutoConfigureAfter(org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class)
public class RedisAutoConfiguration {


}
