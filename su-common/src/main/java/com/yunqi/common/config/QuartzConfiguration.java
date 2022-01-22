package com.yunqi.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 *     Quartz配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Configuration
@AutoConfigureAfter({DruidDataSourceAutoConfigure.class})
public class QuartzConfiguration {

    /**
     * <p>
     *     配置任务调度器
     * </p>
     * @param dataSource 数据源实例
     * @return Scheduler对象
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        // #==============================================================
        // # Quartz参数配置
        // #==============================================================
        Properties prop = new Properties();

        // #==============================================================
        // # Configure Main Scheduler Properties
        // #==============================================================
        // 调度器实例名称 集群中每一个实例都必须使用相同的名称
        prop.put("org.quartz.scheduler.instanceName", "QuartzScheduler");
        // 调度器实例ID 分布式节点ID自动生成
        prop.put("org.quartz.scheduler.instanceId", "AUTO");


        // #==============================================================
        // # Configure JobStore JobStore配置、集群配置 isClustered=true
        // #==============================================================
        // job的存储方式,可以选择存储在内存中或者持久化数据库中
        // 存储在内存:org.quartz.simpl.RAMJobStore
        // 存储在数据库:org.quartz.impl.jdbcjobstore.JobStoreTX
        prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");
        // 数据表前缀，默认QRTZ_
        prop.put("org.quartz.jobStore.tablePrefix", "IMS_SYS_QRTZ_");
        // 是否以集群方式运行
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 调度实例失效的检查时间间隔,默认值20000毫秒 (即20秒)
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        // JobStore处理未按时触发的Job数量,默认值20
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "120");
        // 是否使用事务隔离级别中的可序列化
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "false");
        // 最大能忍受的触发超时时间,默认值60000毫秒 (即60秒)
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // sqlserver 启用
        // prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");

        // #==============================================================
        // # Configure ThreadPool 线程池配置
        // #==============================================================
        // 线程池的实现类
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        // 线程池中的线程数量 至少为1（无默认值）(1-100)
        prop.put("org.quartz.threadPool.threadCount", "10");
        // 线程的优先级（最大为10，最小为1，默认为5）
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // 线程继承初始化线程的上下文类加载器
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        // 设置 Quartz 属性
        factory.setQuartzProperties(prop);

        // 延时启动,应用启动3秒后
        factory.setStartupDelay(3);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factory.setAutoStartup(true);
        return factory;
    }
}
