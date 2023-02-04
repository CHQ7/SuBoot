package com.yunqi.system.models;

import com.yunqi.starter.database.model.BaseModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 系统文件
 * Created by @author CHQ on 2022/12/18
 */
@Data
@Table("ims_sys_file")
@TableMeta("{'mysql-charset':'utf8mb4'}")
@Accessors(chain = true)
public class SysFile extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("文件类型")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String fileType;

    @Column
    @Comment("文件名称")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String fileMd5;

    @Column
    @Comment("文件名称")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String fileName;

    @Column
    @Comment("文件大小")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String fileSizeKb;

    @Column
    @Comment("文件路径")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String filePath;

    @Column
    @Comment("文件后缀")
    @ColDefine(type = ColType.VARCHAR, width = 250)
    private String fileSuffix;


}
