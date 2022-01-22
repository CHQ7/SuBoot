package com.yunqi.system.models;

import com.yunqi.common.base.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 * Created by @author JsckChin on 2021/12/19
 */
@Table("ims_sys_menu")
@Data
public class SysMenu extends BaseModel implements Serializable{

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

    @NotBlank(message = "系统菜单名称不能为空")
    @Column
    @Comment("菜单名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Column
    @Comment("资源类型")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String type;

    @Column
    @Comment("路由")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String router;

    @Column
    @Comment("路由路径")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String path;

    @Column
    @Comment("路由缓存")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean keepAlive;

    @Column
    @Comment("打开方式")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String target;

    @Column
    @Comment("菜单图标")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String icon;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @NotBlank(message = "系统权限唯一编码不能为空")
    @Column
    @Comment("权限唯一编码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String permission;

    @Column
    @Comment("菜单介绍")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    @Column
    @Comment("排序字段")
    @Default("0")
    private Integer location;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<>();


}
