package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统任务
 * Created by @author CHQ on 2022/6/15
 */
@Table("ims_sys_task")
@Data
public class SysTask extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @NotBlank(message = "任务名称不能为空")
    @Column
    @Comment("任务名称")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String name;

    @NotBlank(message = "执行类不能为空")
    @Column
    @Comment("执行类")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String jobClass;

    @NotBlank(message = "定时规则不能为空")
    @Column
    @Comment("定时规则")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String cron;

    @Column
    @Comment("执行参数")
    @ColDefine(type = ColType.TEXT)
    private String data;

    @Column
    @Comment("执行时间")
    private Long  exeAt;

    @Column
    @Comment("执行结果")
    @ColDefine(type = ColType.TEXT)
    private String exeResult;

    @Column
    @Comment("任务说明")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;


}
