package com.yunqi.starter.database.config;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.DaoRunner;
import org.nutz.dao.impl.NutDao;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * Created by @author JsckChin on 2022/1/29
 */
@Configuration
@ConditionalOnClass({Dao.class})
@ConditionalOnExpression("${su.database.enabled:true}")
@EnableConfigurationProperties(DataBaseAutoConfigurationProperties.class)
public class DataBaseAutoConfiguration {



   /* @Bean
    public Dao dao(DataSource dataSource, SpringDaoRunner daoRunner){
        NutDao dao = new NutDao(dataSource);
        dao.setRunner(daoRunner);
        return dao;
    }*/

    @Bean
    @ConditionalOnMissingBean(DaoRunner.class)
    public SpringDaoRunner daoRunner() {
        return new SpringDaoRunner();
    }
}
