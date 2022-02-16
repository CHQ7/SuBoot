package com.yunqi.log.annotation;

import com.yunqi.log.enums.LogType;

import java.lang.annotation.*;

/**
 * 注解日志系统
 * Created by @author JsckChin on 2022/2/16
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SLog {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 日志类型
     * @return 日志类型
     */
    LogType type() default LogType.OTHER;

    /**
     * 是否记录传递参数
     *
     * @return 消息模板
     */
    boolean param() default true;

    /**
     * 记录执行结果
     *
     * @return 消息模板
     */
    boolean result() default true;
}
