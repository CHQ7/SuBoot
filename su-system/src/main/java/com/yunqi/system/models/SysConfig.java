package com.yunqi.system.models;

import com.yunqi.common.base.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统配置参数
 * Created by @author JsckChin on 2021/12/28
 */
@Table("ims_sys_config")
@Data
public class SysConfig extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("参数key")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String configKey;

    @Column
    @Comment("参数的值")
    @ColDefine(type = ColType.VARCHAR, width = 10000)
    private String configValue;

    @NotBlank(message = "系统参数标题不能为空")
    @Column
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String configName;

    @Column
    @Comment("备注")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;
}
