package com.yunqi.starter.mail.configuration;

import com.yunqi.starter.mail.service.IMailService;
import com.yunqi.starter.mail.service.impl.MailServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by @author JsckChin on 2022/1/29
 */
@Configuration
@ConditionalOnClass({MailServiceImpl.class})
@ConditionalOnExpression("${su.mail.enabled:true}")
@EnableConfigurationProperties(MailAutoConfigurationProperties.class)
public class MailAutoConfiguration {

    public MailAutoConfigurationProperties properties;

    public MailAutoConfiguration(MailAutoConfigurationProperties properties){
        this.properties = properties;
    }

    @Bean
    public IMailService iMailService(){
        return new MailServiceImpl(properties);
    }
}
