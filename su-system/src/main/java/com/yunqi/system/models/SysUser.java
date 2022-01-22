package com.yunqi.system.models;

import com.yunqi.common.base.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 系统用户
 * Created by @author JsckChin on 2021/12/19
 */
@Table("ims_sys_user")
@Data
public class SysUser extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Name
	@Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
	private String id;

    @NotBlank(message = "账号不能为空")
    @Column
    @Comment("账号")
	@ColDefine(type = ColType.VARCHAR, width = 120)
    private String name;

    @NotBlank(message = "密码不能为空")
    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String password;

    @Column
    @Comment("密码盐")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String salt;

    @NotBlank(message = "姓名不能为空")
    @Column
    @Comment("姓名")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String nickname;

	@Column
	@Comment("头像")
	@ColDefine(type = ColType.VARCHAR, width = 255)
	private String avatar;

    @Column
	@Comment("邮箱")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String email;

    @Column
    @Comment("手机")
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String mobile;

    @Column
    @Comment("性别(0-未知 1-男 2-女)")
    @ColDefine(type = ColType.INT,width = 1)
    @Default(value = "0")
    private Integer sex;

    @Column
    @Comment("生日")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String birthday;

	// 行为信息

	@Column
	@Comment("是否在线")
	@ColDefine(type = ColType.BOOLEAN)
	private boolean online;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("备注")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    /**
     *  最近登录信息
     */
    @Column
    @Comment("最后登录时间")
    private Long  loginAt;

    @Column
    @Comment("最后登录IP")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String loginIp;

    @Column
    @Comment("最后登录地点")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String loginLocation;

    @Column
    @Comment("浏览器类型")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String loginBrowser;

    @Column
    @Comment("操作系统")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String loginOs;

    @Column
    @Comment("登陆次数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer loginCount;

    @Column
    @Comment("机构")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String deptId;

    /**
     * 关联角色
     */
    @ManyMany(from="userId", relation="ims_sys_user_role", to="roleId")
    private List<SysRole> roles;

    /**
     * 关联机构
     */
    @ManyMany(from = "userId", relation = "ims_sys_user_dept", to = "deptId")
    private List<SysDept> depts;

}
