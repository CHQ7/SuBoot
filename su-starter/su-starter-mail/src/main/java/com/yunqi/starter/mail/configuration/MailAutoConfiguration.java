package com.yunqi.starter.mail.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by @author JsckChin on 2022/1/29
 */
@Configuration
@ConditionalOnExpression("${su.mail.enabled:true}")
@EnableConfigurationProperties(MailAutoConfigurationProperties.class)
public class MailAutoConfiguration {

    @Autowired
    MailAutoConfigurationProperties properties;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl email = new JavaMailSenderImpl();
        email.setHost(properties.getHostName());
        email.setPort(Integer.parseInt(properties.getSmtpPort()));
        email.setUsername(properties.getUserName());
        email.setPassword(properties.getPassword());
        email.setDefaultEncoding(properties.getCharset());
        if(properties.isSsl()){
            Properties prop = new Properties();
            prop.put("mail.smtp.ssl.enable", properties.isSsl());
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            email.setJavaMailProperties(prop);
        }
        return email;
    }
}
