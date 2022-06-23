package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统组织
 * Created by @author CHQ on 2022/6/15
 */
@Table("ims_sys_dept")
@Data
public class SysDept extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("父级ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String parentId;

    @NotBlank(message = "组织名称不能为空")
    @Column
    @Comment("组织名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @NotBlank(message = "系统组织唯一编码不能为空")
    @Column
    @Comment("唯一编码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String code;

    @Column
    @Comment("备注")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    @Column
    @Comment("排序字段")
    private Integer location;

    /** 子菜单 */
    private List<SysDept> children = new ArrayList<>();

}
