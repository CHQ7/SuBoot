package com.yunqi.system.service;

import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysTaskLog;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;


/**
 * Created by @author JsckChin on @date 2019/9/20 2:41 下午.
 */
@Service
public class ISysTaskLogService extends BaseServiceImpl<SysTaskLog> {


    /**
     * 任务列表
     * @param page       页码
     * @param pageSize   每页几条数据
     * @param taskLog    jobName:任务名称
     * @return           分页列表
     */
    public Object list(Integer page, int pageSize, SysTaskLog taskLog){
        Cnd cnd =  Cnd.NEW();
        // 根据任务名称查询
        if(Strings.isNotBlank(taskLog.getJobName())){
            cnd.and("name", "like", "%" + taskLog.getJobName() + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(page, pageSize, cnd);
    }



}
