package com.yunqi.starter.log.aop;

import com.yunqi.starter.log.configuration.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.json.Json;
import org.springframework.stereotype.Component;

/**
 * Created by @author JsckChin on 2022/2/17
 */
@Slf4j
@Aspect
@Component
public class SLogAspect {

    private final LogProperties properties;

    public SLogAspect(LogProperties properties) {
        this.properties = properties;
    }

    /** 定义AOP签名 (切入所有使用SLog鉴权注解的方法) */
    public static final String POINTCUT_SIGN = "@annotation(com.yunqi.starter.log.annotation.SLog)";

    /** 声明AOP签名 */
    @Pointcut(POINTCUT_SIGN)
    public void pointcut() {}


    @AfterReturning(pointcut = "pointcut()", returning = "res")
    public void  doAroundReturning(JoinPoint joinPoint, Object res) {
        if (properties != null && properties.isEnabled()) {
            log.warn("正常处理流程:{}", Json.toJson(res));
        }
    }


    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        if (properties != null && properties.isEnabled()) {
            log.warn("异常处理流程:{}", Json.toJson(ex));
        }
    }
}
