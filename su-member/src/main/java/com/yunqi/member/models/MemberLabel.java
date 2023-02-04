package com.yunqi.member.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户标签
 * Created by @author CHQ on 2022/11/2
 */
@Data
@Table("ims_member_label")
@TableMeta("{'mysql-charset':'utf8mb4'}")
public class MemberLabel extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = { @EL("uuid()") })
    private String id;

    @NotBlank(message = "标签名称不能为空")
    @Length(min=1,max=10,message = "标签名称,最大长度为10位")
    @Column
    @Comment("标签名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Length(min=1,max=50,message = "标签备注,最大长度为50位")
    @Column
    @Comment("标签备注")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String note;

}
