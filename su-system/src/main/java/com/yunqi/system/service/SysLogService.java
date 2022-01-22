package com.yunqi.system.service;

import com.yunqi.common.base.service.BaseServiceImpl;
import com.yunqi.system.models.SysLog;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by @author JsckChin on 2021/10/1
 */
@Service
@Transactional
public class SysLogService extends BaseServiceImpl<SysLog> {


    /**
     * 异步创建日志
     * @param log   日志
     */
    @Async
    public void asyncByCreate(SysLog log) {
        this.insert(log);
    }

    /**
     * 日志列表
     * @param pageNumber    页码
     * @param pageSize      每页几条数据
     * @param beginTime     开始日期
     * @param endTime       结束日期
     * @param sysLog        IP、操作人、IP地址
     * @return              分页列表
     */
    public Object list(Integer pageNumber, int pageSize, String beginTime, String endTime,SysLog sysLog){
        Cnd cnd =  Cnd.NEW();
        // 查询:IP
        if (Strings.isNotBlank(sysLog.getIp())) {
            cnd.and("ip","=",sysLog.getIp());
        }
        // 模糊查询:操作人
        if (Strings.isNotBlank(sysLog.getName())) {
            cnd.and("name","like","%" + sysLog.getName() + "%");
        }
        // 模糊查询:IP地址
        if (Strings.isNotBlank(sysLog.getLocation())) {
            cnd.and("location","like","%" + sysLog.getLocation() + "%");
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

}
