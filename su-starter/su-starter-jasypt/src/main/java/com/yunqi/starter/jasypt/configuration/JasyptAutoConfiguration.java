package com.yunqi.starter.jasypt.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by @author JsckChin on 2022/1/29
 */
@Slf4j
@Configuration
@ConditionalOnExpression("${su.jasypt.enabled:true}") // 默认启动组件
@AutoConfigureAfter({StringEncryptor.class})
@EnableConfigurationProperties(JasyptAutoConfigurationProperties.class) //使配置文件生效
public class JasyptAutoConfiguration {

    public JasyptAutoConfigurationProperties properties;

    public JasyptAutoConfiguration(JasyptAutoConfigurationProperties properties){
        this.properties = properties;
    }

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        // 加密盐值
        config.setPassword(properties.getPassword());
        // 设置加密算法的值
        config.setAlgorithm(properties.getAlgorithm());
        // 设置用于获取加密密钥的散列迭代次数
        config.setKeyObtentionIterations(properties.getKeyObtentionIterations());
        // 设置要创建的加密器池的大小
        config.setPoolSize(properties.getPoolSize());
        // 设置盐生成器
        config.setSaltGeneratorClassName(properties.getSaltGeneratorClassName());
        // 设置字符串输出将被编码的形式
        config.setStringOutputType(properties.getStringOutputType());
        encryptor.setConfig(config);
        return encryptor;
    }

}