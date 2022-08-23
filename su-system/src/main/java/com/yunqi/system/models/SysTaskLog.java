package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 任务日志
 * Created by @author CHQ on 2022/6/15
 */
@Data
@Table("ims_sys_task_log")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class SysTaskLog extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("任务名称")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String jobName;

    @Column
    @Comment("任务名称")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String jobGroup;

    @Column
    @Comment("调用目标字符串")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String invokeTarget;

    @Column
    @Comment("日志信息")
    @ColDefine(type = ColType.TEXT)
    private String jobMessage;

    @Column
    @Comment("执行状态 （0正常 1失败）")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer status;

    @Column
    @Comment("异常信息")
    @ColDefine(type = ColType.TEXT)
    private String exceptionInfo;


    @Column
    @Comment("开始时间")
    private Long  startTime;

    @Column
    @Comment("结束时间")
    private Long  endTime;


}
