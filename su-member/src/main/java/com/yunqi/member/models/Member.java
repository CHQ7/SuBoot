package com.yunqi.member.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 用户
 * Created by @author CHQ on 2022/9/30
 */
@Data
@Table("ims_member")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class Member extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = { @EL("uuid()") })
    private String id;

    // ================ 账号信息 ========================

    @Column
    @Comment("openid")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String openid;

    @Column
    @Comment("会话密钥")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String sessionKey;

    @Column
    @Comment("昵称")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String nickname;

    @Column
    @Comment("手机")
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String mobile;

    @Column
    @Comment("邮箱")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String email;

    @Column
    @Comment("头像")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String avatar;

    @Column
    @Comment("性别(0-保密 1-男 2-女)")
    @ColDefine(type = ColType.INT,width = 1)
    private int sex;

    @Column
    @Comment("年龄")
    @Default(value = "0")
    private Integer age;

    @Column
    @Comment("生日")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String birthday;

    // ================ 关联信息 ========================

    @Column
    @Comment("等级ID")
    private int levelId;

    // ================ 安全信息 ========================

    @Column
    @Comment("密盐")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String salt;

    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String password;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean status;

    // ================ 实名信息 ========================

    @Column
    @Comment("实名")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String realName;

    @Column
    @Comment("证件号码")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String idCard;

    @Column
    @Comment("是否实名认证")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean certification;

    // ================ 钱包信息 ========================

    @Column
    @Comment("信贷  以分为单位")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Long credit1;

    @Column
    @Comment("信贷  以分为单位")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Long credit2;

    @Column
    @Comment("信贷  以分为单位")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Long credit3;

    @Column
    @Comment("信贷  以分为单位")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Long credit4;

    // ================ 所属地区 ========================

    @Column
    @Comment("国籍")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String nationality;

    @Column
    @Comment("省")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String province;

    @Column
    @Comment("市")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String city;

    @Column
    @Comment("县/区")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String area;

    @Column
    @Comment("地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String address;

    @Column
    @Comment("邮编")
    @ColDefine(type = ColType.VARCHAR, width = 30)
    private String zipcode;

    // ================ 登录信息 ========================

    @Column
    @Comment("上次登陆时间")
    private Long  loginAt;

    @Column
    @Comment("上次登陆IP")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String loginIp;

    @Column
    @Comment("登陆次数")
    @ColDefine(type = ColType.INT)
    private Integer loginCount;

    // ================ 备注信息 ========================

    @Column
    @Comment("备注")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String note;

    // ================ 关联信息 ========================

    @One(field = "levelId")
    public MemberLevel level;


}
