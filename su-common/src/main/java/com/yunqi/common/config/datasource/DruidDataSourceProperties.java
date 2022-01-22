package com.yunqi.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * <p>
 *     Druid配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Data
@Configuration
public class DruidDataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    /**
     * 初始化时建立物理连接的个数
     */
    private Integer initialSize = 5;

    /**
     * 最小连接池数量
     */
    private Integer minIdle = 10;

    /**
     * 最大连接池数量
     */
    private Integer maxActive = 50;

    /**
     * 获取连接时最大等待时间，单位毫秒
     */
    private Integer maxWait = 6000;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private Integer timeBetweenEvictionRunsMillis = 60000;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private Integer minEvictableIdleTimeMillis = 300000;

    /**
     * 检测连接是否有效的sql
     * mysql中为 select 'x'
     * oracle中为 select 1 from dual
     */
    private String validationQuery = "select 'x'";

    /**
     * 当连接空闲时,是否执行连接测试
     */
    private Boolean testWhileIdle = true;

    /**
     * 在连接归还到连接池时是否测试该连接
     */
    private Boolean testOnBorrow = false;

    /**
     * 在连接归还到连接池时是否测试该连接
     */
    private Boolean testOnReturn = false;

    /**
     * PSCache Mysql下建议关闭
     */
    private Boolean poolPreparedStatements = false;

    private Integer maxPoolPreparedStatementPerConnectionSize = -1;

    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    private String connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=2000";

    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    private String filters = "stat,wall";

    /**
     * <p>
     *     初始化Druid数据源
     * </p>
     * @param dataSource
     */
    public void initDruidDataSource(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        dataSource.setConnectionProperties(connectionProperties);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
