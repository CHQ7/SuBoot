package com.yunqi.member.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * 用户等级
 * Created by @author CHQ on 2022/11/2
 */
@Data
@Table("ims_member_level")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class MemberLevel  extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @PositiveOrZero(message = "等级权重必须是正数")
    @Id(auto=false)
    @Column
    @Comment("ID,等级权重")
    private int id;

    @NotBlank(message = "等级名字不能为空")
    @Column
    @Comment("等级名字")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Column
    @Comment("折扣:可享折扣权益")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String discount;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean status;


}
