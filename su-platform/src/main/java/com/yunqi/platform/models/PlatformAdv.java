package com.yunqi.platform.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 幻灯片
 * Created by @author CHQ on 2022/10/7
 */
@Data
@Table("ims_platform_adv")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class PlatformAdv extends BaseModel implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = { @EL("uuid()") })
    private String id;

    @NotBlank(message = "幻灯片标题不能为空")
    @Column
    @Comment("幻灯片标题")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String title;

    @Column
    @Comment("幻灯片图片")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String thumb;

    @Column
    @Comment("链接地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String url;

    @Column
    @Comment("状态,1显示 0隐藏")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean status;

    @Column
    @Comment("排序字段")
    @Default("0")
    private Integer location;
}
