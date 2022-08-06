package com.yunqi.system.service;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.log.model.SysLog;
import com.yunqi.starter.log.provider.ISysLogProvider;
import org.nutz.dao.Cnd;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 操作日志
 * Created by @author CHQ on 2022/6/15
 */
@Service
@Transactional
public class ISysLogService extends BaseServiceImpl<SysLog>  implements ISysLogProvider{


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
     * @param sysLog    操作日志记录
     */
    @Async
    public void saveLog(SysLog sysLog) {
        this.insert(sysLog);
    }

}
