package com.yunqi.common.Job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 *     测试任务类
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Test Job hi::Hello");

        // 记录日志
    }
}
