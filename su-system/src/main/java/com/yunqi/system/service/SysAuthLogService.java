package com.yunqi.system.service;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.yunqi.starter.common.utils.IPUtil;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysAuthLog;
import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录日志
 * Created by @author JsckChin on 2021/12/26
 */
@Service
@Transactional
public class SysAuthLogService extends BaseServiceImpl<SysAuthLog> {

    /**
     * 日志列表
     * @param pageNumber    页码
     * @param pageSize      每页几条数据
     * @param beginTime     开始日期
     * @param endTime       结束日期
     * @param sysAuthLog    操作人
     * @return              分页列表
     */
    public Object list(Integer pageNumber, int pageSize, String beginTime, String endTime, SysAuthLog sysAuthLog){
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:操作人
        if (Strings.isNotBlank(sysAuthLog.getName())) {
            cnd.and("name","like","%" + sysAuthLog.getName() + "%");
        }
        // 时间范围
        if(Strings.isNotBlank(beginTime) || Strings.isNotBlank(endTime)){
            // 字符串分割转化为数组
            cnd.and("createdAt",">=", Times.D(beginTime).getTime());
            cnd.and("createdAt","<=", Times.D(endTime).getTime());
        }
        cnd.desc("createdAt");
        return this.listPage(pageNumber, pageSize, cnd);
    }

    /**
     * 异步创建日志
     * @param log   日志
     */
    @Async
    public void asyncByCreate(HttpServletRequest req,SysAuthLog log) {
        // ========================================== 开始请求日志 ==========================================
        // 获取终端信息
        final UserAgent ua = UserAgentUtil.parse(req.getHeader("User-Agent"));

        // *========数据库日志=========*
        // 获取请求地址
        log.setUrl(req.getRequestURI());
        // 获取请求方式
        log.setMethod(req.getMethod());
        // 获取客户端
        log.setBrowser(ua.getBrowser().getName() + "_" + ua.getVersion());
        // 获取操作系统
        log.setOs(ua.getPlatform().getName() + "_"  + ua.getOsVersion());
        // 获取IP地址
        String ip = Lang.getIP(req);
        log.setIp(ip);
        // 获取IP归属地
        log.setLocation(IPUtil.getIPAddress(ip));
        this.insert(log);
    }
}
