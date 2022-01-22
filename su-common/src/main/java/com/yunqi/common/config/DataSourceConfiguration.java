package com.yunqi.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.yunqi.common.config.datasource.DruidDataSourceProperties;
import com.yunqi.common.config.nutz.SpringDaoRunner;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.DaoRunner;
import org.nutz.dao.impl.NutDao;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p>
 *     数据源配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Configuration
@ConditionalOnClass({Dao.class})
@AutoConfigureAfter({DruidDataSourceAutoConfigure.class})
public class DataSourceConfiguration {

    @Resource
    private DruidDataSourceProperties druidDataSourceProperties;

    /**
     * 创建主数据源
     * Primary 表示这个数据源是默认数据源
     * ConfigurationProperties  读取application.properties中的配置参数映射成为一个对象
     */
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        druidDataSourceProperties.initDruidDataSource(dataSource);
        return dataSource;
    }

    @Bean
    public Dao dao(DataSource dataSource, SpringDaoRunner daoRunner){
        NutDao dao = new NutDao(dataSource);
        dao.setRunner(daoRunner);
        return dao;
    }

    @Bean
    @ConditionalOnMissingBean(DaoRunner.class)
    public SpringDaoRunner daoRunner() {
        return new SpringDaoRunner();
    }




}
