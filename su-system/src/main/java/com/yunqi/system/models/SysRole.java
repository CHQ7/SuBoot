package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 系统角色
 * Created by @author JsckChin on 2021/12/19
 */
@Table("ims_sys_role")
@Data
public class SysRole extends BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @NotBlank(message = "系统角色名称不能为空")
    @Column
    @Comment("名字")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String name;

    @NotBlank(message = "系统角色唯一编码不能为空")
    @Column
    @Comment("唯一编码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String code;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("排序字段")
    @ColDefine(type = ColType.INT)
    @Default(value="0")
    private Integer location;

    @Column
    @Comment("备注")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    /**
     * 关联权限
     */
    @ManyMany(from = "roleId", relation = "ims_sys_role_menu", to = "menuId")
    protected List<SysMenu> menus;

    /**
     * 关联用户
     */
    @ManyMany(from = "roleId", relation = "ims_sys_user_role", to = "userId")
    private List<SysUser> users;


}
