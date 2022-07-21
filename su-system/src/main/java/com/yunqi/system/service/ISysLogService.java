package com.yunqi.system.service;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.log.model.SysLog;
import com.yunqi.starter.log.provider.ISysLogProvider;
import org.nutz.dao.Cnd;
import org.nutz.lang.Times;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by @author CHQ on 2022/6/15
 */
@Service
@Transactional
public class ISysLogService extends BaseServiceImpl<SysLog>  implements ISysLogProvider{


    /**
     * 日志列表
     * @param page          页码
     * @param pageSize      每页几条数据
     * @param beginTime     开始日期
     * @param endTime       结束日期
     * @param sysLog        IP、操作人、IP地址
     * @return              分页列表
     */
    public Object list(Integer page, int pageSize, String beginTime, String endTime,SysLog sysLog){
        Cnd cnd =  Cnd.NEW();
        // 查询:IP
        if (Strings.isNotBlank(sysLog.getIp())) {
            cnd.and("ip","=",sysLog.getIp());
        }

        // 模糊查询:IP地址
        if (Strings.isNotBlank(sysLog.getLocation())) {
            cnd.and("location","like","%" + sysLog.getLocation() + "%");
        }
        // 时间范围:根据时间戳范围查询
        if(Strings.isNotBlank(beginTime) || Strings.isNotBlank(endTime)){
            cnd.and("createdAt",">=", beginTime);
            cnd.and("createdAt","<=", endTime);
        }
        cnd.desc("createdAt");
        return this.listPage(page, pageSize, cnd);
    }

    /**
     * 异步创建日志
     * @param sysLog
     */
    @Async
    public void saveLog(SysLog sysLog) {
        this.insert(sysLog);
    }

}
