package com.yunqi.common.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 *     Quartz管理
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Component
public class QuartzManager {

    @Resource
    private Scheduler scheduler;

    private static final String JOB_GROUP_NAME = "JobGroup";
    private static final String TRIGGER_GROUP_NAME = "TriggerGroup";

    /** 执行目标key */
    private static final String TASK_PROPERTIES = "TASK_PROPERTIES";


    /**
     * 开始执行所有任务
     */
    public void startJob() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 添加任务
     * @param jobName 任务名称
     * @param klass 任务类
     * @param cron cron表达式
     * @param data 额外数据
     */
    public void addJob(String jobName,  String klass, String cron, String data, boolean status) {
        try {
            // 构建job信息
            JobDetail jobDetail = buildJobDetail(jobName,  Class.forName(klass), data);

            // 构建触发器
            CronTrigger trigger = buildTrigger(jobName, cron);

            // 判断是否存在
            if(exist(jobName)){
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                deleteJob(jobName);
            }

            // 设置调度器
            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if(!status){
                pauseJob(jobName);
            }
        } catch (Exception e) {
            log.error("创建定时任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新任务时间表达式
     * @param jobName 任务名称
     * @param jobGroup 任务分组
     * @param cron cron表达式
     */
    public void updateJob(String jobName,String jobGroup, String cron) {
        try {
            // 创建新 Trigger 对象
            CronTrigger newTrigger = buildTrigger(jobName,  cron);
            // 修改调度
            scheduler.rescheduleJob(buildTriggerKey(jobName), newTrigger);
        } catch (Exception e) {
            log.error("更新定时任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 暂停所有任务
     */
    public void pauseAllJob() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 恢复所有任务
     */
    public void resumeAllJob(){
        try {
            scheduler.resumeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 暂停定时任务
     * @param jobName 任务名称
     */
    public void pauseJob(String jobName) {
        try {
            scheduler.pauseJob(buildJobKey(jobName));
        } catch (Exception e) {
            log.error("暂停任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 恢复任务
     * @param jobName 任务名称
     */
    public void resumeJob(String jobName)  {
        try {
            scheduler.resumeJob(buildJobKey(jobName));
        } catch (Exception e) {
            log.error("恢复任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 立刻执行
     * @param jobName 任务名称
     */
    public void runJob(String jobName) {
        try {
            scheduler.triggerJob(buildJobKey(jobName));
        } catch (Exception e) {
            log.error("立刻执行任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除任务
     * @param jobName 任务名称
     */
    public void deleteJob(String jobName){
        try {
            // 如果存在这个任务，则删除
            if(exist(jobName)){
                scheduler.deleteJob(buildJobKey(jobName));
            }
        } catch (Exception e) {
            log.error("删除定时任务异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 清除所有任务
     */
    public void clear()  {
        try {
            scheduler.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 任务是否存在
     * @param jobName 任务名称
     * @return true if a Job exists with the given identifier
     */
    public boolean exist(String jobName){
        try {
            return scheduler.checkExists(buildJobKey(jobName));
        } catch (Exception e) {
            log.error("任务是否存在异常", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 构建任务键对象
     * @param jobName
     * @return
     */
    public JobKey buildJobKey(String jobName) {
        return  JobKey.jobKey(jobName, JOB_GROUP_NAME);
    }

    /**
     * 构建任务触发对象
     * @param jobName
     * @return
     */
    public TriggerKey buildTriggerKey(String jobName) {
        return  TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
    }


    /**
     * 构建触发器
     * @param jobName 任务名称
     * @param cron cron
     * @return CronTrigger
     */
    public CronTrigger buildTrigger(String jobName, String cron) {
        return TriggerBuilder.newTrigger()
                .withIdentity(buildTriggerKey(jobName))
                // 定时任务不触发立即执行，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .build();
    }


    /**
     * 构建任务信息
     * @param jobName 任务名称
     * @param data 额外数据
     * @return  JobDetail
     */
    public JobDetail buildJobDetail(String jobName, Class<?> klass, String data){
        JobBuilder builder = JobBuilder.newJob((Class<? extends Job>) klass).withIdentity(buildJobKey(jobName));
        JobDataMap newJobDataMap = Strings.isBlank(data)? new JobDataMap() : new JobDataMap(Json.fromJson(NutMap.class, data));
        newJobDataMap.put(TASK_PROPERTIES, jobName);
        // 解析传递的字符串Json参数
        builder.setJobData(newJobDataMap);
        return builder.build();
    }
}
