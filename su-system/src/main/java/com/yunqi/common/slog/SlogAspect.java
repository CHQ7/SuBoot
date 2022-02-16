package com.yunqi.common.slog;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.yunqi.common.annotation.Slog;
import com.yunqi.common.utils.HttpContextUtil;
import com.yunqi.common.utils.IPUtil;
import com.yunqi.starter.security.utils.SecurityUtil;
import com.yunqi.system.models.SysLog;
import com.yunqi.system.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author JsckChin on 2022/1/22
 */
@Aspect
@Component
public class SlogAspect {

    @Resource
    private SysLogService sysLogService;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.yunqi.common.annotation.Slog)")
    public void slog() {}


    /**
     * 切面 配置通知
     * 处理正常结果
     * @param joinPoint 切入点
     * @param res       返回结果
     * @return
     */
    @AfterReturning(pointcut = "slog()", returning = "res")
    public void  doAroundReturning(JoinPoint joinPoint, Object res) {
        handleLog(joinPoint, null, res);
    }

    /**
     * 切面 配置通知
     * 处理异常结果
     * @param joinPoint 切入点
     * @param ex        异常对象
     */
    @AfterThrowing(pointcut = "slog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        handleLog(joinPoint, ex, null);
    }


    /**
     * 本方法通常由aop拦截器调用
     * @param joinPoint  切入点
     * @param ex         异常对象
     * @param res        返回结果
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception ex, Object res){
        // 获取注解
        Slog slog = getAnnotationLog(joinPoint);
        if(slog == null){
            return;
        }
        // ========================================== 开始请求日志 ==========================================
        long beginTime = System.currentTimeMillis();

        // *========数据库日志=========* //
        SysLog sysLog = new SysLog();
        // 设置操作模块
        sysLog.setTag(slog.title());
        // 设置业务类型
        sysLog.setMsg(slog.type().getMessage());
        // 获取请求方法
        sysLog.setSrc(joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName());
        // 设置请求状态
        sysLog.setStatus(0);
        // 获取请求终端信息
        terminal(sysLog, slog.param());
        // 是否需要保存请求结果
        if(slog.result()){
            sysLog.setResult(Json.toJson(res));
        }
        // 记录异常消息
        if(ex != null){
            sysLog.setStatus(1);
            sysLog.setResult( StrUtil.sub(ex.getMessage(),0, 2000));
        }
        // 处理耗时(毫秒)
        sysLog.setProcessingAt(System.currentTimeMillis() - beginTime);
        // ========================================== 结束请求日志 ==========================================
        // 异步记录日志
        sysLogService.asyncByCreate(sysLog);
    }

    /**
     * 转换request请求参数
     * @param paramMap  获取请求参数
     * @return          请求参数字符串
     */
    private String convertMap(Map<String, String[]> paramMap) {
        Map<String, String> param = new HashMap<>();
        for (String key : paramMap.keySet()) {
            param.put(key, paramMap.get(key)[0]);
        }
        return Json.toJson(param);
    }


    /**
     * 获取Slog
     * @param joinPoint 切入点
     * @return          Slog
     */
    private Slog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Slog.class);
        }
        return null;
    }

    /**
     * 获取请求终端信息
     * @param sysLog  系统日志
     * @param isParam 是否需要保存请求参数
     */
    private void terminal(SysLog sysLog, boolean isParam){
        // 获取请求
        HttpServletRequest req  = HttpContextUtil.getReq();
        // 获取终端信息
        final UserAgent ua = UserAgentUtil.parse(req.getHeader("User-Agent"));
        // 获取请求地址
        sysLog.setUrl(req.getRequestURI());
        // 获取请求方式
        sysLog.setMethod(req.getMethod());
        // 设置终端信息
        sysLog.setBrowser(ua.getBrowser().getName() + "_" + ua.getVersion());
        sysLog.setOs(ua.getPlatform().getName() + "_"  + ua.getOsVersion());
        // 是否需要保存请求参数
        if(isParam){
            sysLog.setParam(convertMap(req.getParameterMap()));
        }
        // 设置创建时间
        sysLog.setCreatedAt(System.currentTimeMillis());
        // 设置操作人
        sysLog.setName(SecurityUtil.getUserNickname());
        sysLog.setCreatedById(SecurityUtil.getUserId());
        sysLog.setCreatedBy(SecurityUtil.getUserNickname());
        String ip = Lang.getIP(req);
        // 获取IP
        sysLog.setIp(ip);
        // 获取IP归属地
        sysLog.setLocation(IPUtil.getIPAddress(ip));
    }
}
