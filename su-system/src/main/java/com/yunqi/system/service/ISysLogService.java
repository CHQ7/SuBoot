package com.yunqi.system.service;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.log.model.SLogRecord;
import com.yunqi.starter.log.provider.ILogRecordProvider;
import com.yunqi.system.models.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 操作日志
 * Created by @author CHQ on 2022/6/15
 */
@Slf4j
@Service
@Transactional
public class ISysLogService extends BaseServiceImpl<SysLog>  implements ILogRecordProvider {


    /**
     * 操作日志列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<SysLog> list(QueryBody query){
        Cnd cnd =  Cnd.NEW();
        // 查询:IP
        if (Strings.isNotBlank(query.getString("ip"))) {
            cnd.and("ip","=", query.getString("ip"));
        }

        // 模糊查询:IP地址
        if (Strings.isNotBlank(query.getString("location"))) {
            cnd.and("location","like","%" + query.getString("location") + "%");
        }
        // 时间范围:根据时间戳范围查询
        if(Strings.isNotBlank(query.getString("beginTime"))
                || Strings.isNotBlank(query.getString("endTime"))){
            cnd.and("createdAt",">=", query.getString("beginTime"));
            cnd.and("createdAt","<=", query.getString("endTime"));
        }
        cnd.desc("createdAt");
        return this.listPage(query.page(), query.pageSize(), cnd);
    }

    /**
     * 异步创建日志
     * @param logRecord    操作日志记录
     */
    @Async
    @Override
    public void record(SLogRecord logRecord) {
        SysLog sysLog = new SysLog();
        sysLog.setTag(logRecord.getTag());
        sysLog.setMsg(logRecord.getMsg());
        sysLog.setUrl(logRecord.getUrl());
        sysLog.setMethod(logRecord.getMethod());
        sysLog.setSrc(logRecord.getSrc());
        sysLog.setIp(logRecord.getIp());
        sysLog.setLocation(logRecord.getLocation());
        sysLog.setBrowser(logRecord.getBrowser());
        sysLog.setOs(logRecord.getOs());
        sysLog.setParam(logRecord.getParam());
        sysLog.setResult(logRecord.getResult());
        sysLog.setSuccess(logRecord.getSuccess());
        sysLog.setExecuteTime(logRecord.getExecuteTime());
        sysLog.setCreatedById(logRecord.getOperateUserId());
        sysLog.setCreatedBy(logRecord.getOperateUserName());
        sysLog.setUpdatedById(logRecord.getOperateUserId());
        sysLog.setUpdatedBy(logRecord.getOperateUserName());
        this.insert(sysLog);
    }
}
