package com.yunqi.system.service;


import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.starter.quartz.entity.QuartzJob;
import com.yunqi.starter.quartz.provider.QuartzManager;
import com.yunqi.system.models.SysTask;
import org.nutz.dao.Cnd;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by @author JsckChin on @date 2019/9/20 2:41 下午.
 */
@Service
public class ISysTaskService extends BaseServiceImpl<SysTask> {


    @Resource
    private QuartzManager quartzManager;

    /**
     * 任务列表
     * @param page       页码
     * @param pageSize   每页几条数据
     * @param task       name:任务名称
     * @return           分页列表
     */
    public Object list(Integer page, int pageSize, SysTask task){
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:任务名称
        if(Strings.isNotBlank(task.getName())){
            cnd.and("name", "like", "%" + task.getName() + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(page, pageSize, cnd);
    }

    /**
     * 初始化任务
     */
    public void init() {
        // 清空所有任务
        quartzManager.clear();
        // 加载数据库所有的任务
        List<SysTask> taskList = this.query(Cnd.NEW());
        for (SysTask task : taskList) {
            QuartzJob qj = formatQuartzJob(task);
            // 添加任务
            quartzManager.add(qj);
            // 暂停任务
            if(!task.isDisabled()){
                quartzManager.pause(qj);
            }
        }
    }


    /**
     * 创建任务
     * @param task 任务
     */
    @Transactional
    public SysTask create(SysTask task) {
        // 检查:角色名称是否存在
        if (this.count(Cnd.where("name","=", task.getName())) > 0) {
            throw new BizException("任务名称已存在");
        }
        // 检查Cron表达式的有效性
        if(!CronExpression.isValidExpression(task.getCron())){
            throw new BizException("Cron表达式的无效");
        }
        // 创建任务
        task = this.insert(task);

        //#==========================
        // 创建任务
        //#==========================
        QuartzJob qj = formatQuartzJob(task);
        quartzManager.delete(qj);
        quartzManager.add(qj);
        // 暂停任务
        if(!task.isDisabled()){
            quartzManager.pause(qj);
        }
        return task;
    }

    /**
     * 更新任务
     * @param task 任务
     * @return     返回更新的记录条数
     */
    @Transactional
    public int update(SysTask task) {
        SysTask oldTask = this.fetch(task.getId());
        if(oldTask == null){
            throw new BizException("未知任务");
        }
        // 检查:角色名称是否存在
        if(!Strings.sBlank(oldTask.getName()).equalsIgnoreCase(task.getName())) {
            if (this.count(Cnd.where("name","=", task.getName())) > 0) {
                throw new BizException("任务名称已存在");
            }
        }
        // 检查Cron表达式的有效性
        if(!CronExpression.isValidExpression(task.getCron())){
            throw new BizException("Cron表达式的无效");
        }
        //#==========================
        // 更新任务
        //#==========================
        QuartzJob qj = formatQuartzJob(task);
        quartzManager.delete(qj);
        quartzManager.add(qj);
        if(!task.isDisabled()){
            quartzManager.pause(qj);
        }
        return this.updateIgnoreNull(task);
    }

    /**
     * 删除任务
     * @param id    任务ID
     * @return      返回更新的记录条数
     */
    @Transactional
    public int deleteById(String id) {
        SysTask task = this.fetch(id);
        //#==========================
        // 删除任务
        //#==========================
        quartzManager.delete(formatQuartzJob(task));
        return this.delete(id);
    }

    /**
     * 立即执行
     * @param id
     */
    public void run(String id) {
        SysTask task = this.fetch(id);
        //#==========================
        // 立即执行
        //#==========================
        QuartzJob qj = formatQuartzJob(task);
        if(!quartzManager.exist(qj)){
            quartzManager.add(qj);
            if(!task.isDisabled()){
                quartzManager.pause(qj);
            }
        }
        quartzManager.run(qj);
    }

    private QuartzJob formatQuartzJob(SysTask task) {
        QuartzJob qj = new QuartzJob();
        qj.setJobName(task.getId());
        qj.setJobGroup(task.getId());
        qj.setClassName(task.getJobClass());
        qj.setCron(task.getCron());
        qj.setComment(task.getNote());
        qj.setDataMap(task.getData());
        return qj;
    }
}
