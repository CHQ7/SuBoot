package com.yunqi.common.config;

import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     文件加密秘钥及算法配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Configuration
public class JasyptConfiguration {

    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        // 加密盐值
        config.setPassword("egsnhm");
        // 设置加密算法的值
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        // 设置用于获取加密密钥的散列迭代次数
        config.setKeyObtentionIterations(StandardPBEByteEncryptor.DEFAULT_KEY_OBTENTION_ITERATIONS);
        // 设置要创建的加密器池的大小
        config.setPoolSize("1");
        // 设置盐生成器
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        // 设置字符串输出将被编码的形式
        config.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_BASE64);
        encryptor.setConfig(config);
        return encryptor;
    }
}
