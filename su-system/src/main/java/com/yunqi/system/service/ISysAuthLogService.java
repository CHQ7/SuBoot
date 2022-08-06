package com.yunqi.system.service;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.common.utils.IPUtil;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysAuthLog;
import org.nutz.dao.Cnd;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录日志
 * Created by @author CHQ on 2022/6/15
 */
@Service
@Transactional
public class ISysAuthLogService extends BaseServiceImpl<SysAuthLog> {


    /**
     * 登录日志列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<SysAuthLog> list(QueryBody query){
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:操作人
        if (Strings.isNotBlank(query.getString("name"))) {
            cnd.and("name","like","%" + query.getString("name") + "%");
        }
        // 时间范围:根据时间戳范围查询
        if(Strings.isNotBlank(query.getString("beginTime")) || Strings.isNotBlank(query.getString("endTime"))){
            cnd.and("createdAt",">=", query.getString("beginTime"));
            cnd.and("createdAt","<=", query.getString("endTime"));
        }
        cnd.desc("createdAt");
        return this.listPage(query.page(), query.pageSize(), cnd);
    }

    /**
     * 异步创建日志
     * @param log   日志
     */
    @Async
    public void saveLog(HttpServletRequest req,SysAuthLog log) {
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
