package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 系统登录日志
 * Created by @author CHQ on 2022/6/15
 */

@Data
@Table("ims_sys_log")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class SysLog extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("日志标签")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String tag;

    @Column
    @Comment("业务类型")
    @ColDefine(type = ColType.TEXT)
    private String msg;

    @Column
    @Comment("请求地址")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String url;

    @Column
    @Comment("请求方式")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String method;

    @Column
    @Comment("执行类")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String src;

    @Column
    @Comment("IP地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String ip;

    @Column
    @Comment("IP地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String location;

    @Column
    @Comment("浏览器类型")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String browser;

    @Column
    @Comment("操作系统")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String os;

    @Column
    @Comment("请求参数")
    @ColDefine(type = ColType.TEXT)
    private String param;

    @Column
    @Comment("执行结果")
    @ColDefine(type = ColType.TEXT)
    private String result;

    @Column
    @Comment("是否调用成功")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean success;

    @Column
    @Comment("处理耗时")
    @Default(value = "0")
    private Long executeTime;
}
