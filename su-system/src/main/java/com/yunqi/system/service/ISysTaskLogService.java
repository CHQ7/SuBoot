package com.yunqi.system.service;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysTaskLog;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;


/**
 * Created by @author CHQ on 2022/6/15
 */
@Service
public class ISysTaskLogService extends BaseServiceImpl<SysTaskLog> {


    /**
     * 任务日志列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<SysTaskLog> list(QueryBody query){
        Cnd cnd =  Cnd.NEW();
        // 根据任务名称查询
        if(Strings.isNotBlank(query.getString("name"))){
            cnd.and("name", "like", "%" + query.getString("name") + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(query.page(), query.pageSize(), cnd);
    }



}
