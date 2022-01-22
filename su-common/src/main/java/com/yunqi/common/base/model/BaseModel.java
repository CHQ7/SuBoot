package com.yunqi.common.base.model;

import com.yunqi.common.utils.SecurityUtil;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.interceptor.annotation.PrevInsert;
import org.nutz.dao.interceptor.annotation.PrevUpdate;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;

import java.io.Serializable;

/**
 * 基础模型
 * Created by @author JsckChin on 2022/1/19
 */
@Data
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Comment("创建ID")
    @PrevInsert(els = @EL("$me.createdByUid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String createdById;

    @Column
    @Comment("创建人")
    @PrevInsert(els = @EL("$me.createdBy()"))
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String createdBy;

    @Column
    @Comment("创建时间")
    @PrevInsert(now = true)
    private Long  createdAt;

    @Column
    @Comment("修改ID")
    @PrevInsert(els = @EL("$me.updatedByUid()"))
    @PrevUpdate(els = @EL("$me.updatedByUid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    protected String updatedById;

    @Column
    @Comment("修改人")
    @PrevInsert(els = @EL("$me.updatedBy()"))
    @PrevUpdate(els = @EL("$me.updatedBy()"))
    @ColDefine(type = ColType.VARCHAR, width = 100)
    protected String updatedBy;

    @Column
    @Comment("修改时间")
    @PrevInsert(now = true)
    @PrevUpdate(now = true)
    private Long updatedAt;

    @Column
    @Comment("删除标记")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean delFlag;

    @Override
    public String toString() {
        return Json.toJson(this, JsonFormat.compact());
    }


   /**
     * 获取创建人ID
     * @return
     */
    public String createdByUid() {
        return getUidString(getCreatedById());
    }

    /**
     * 获取创建人
     * @return
     */
    public String createdBy() {
        return getUserNickname(getCreatedBy());
    }

    /**
     * 获取修改ID
     * @return
     */
    public String updatedByUid() {
        return getUidString(getUpdatedById());
    }

    /**
     * 获取修改人
     * @return
     */
    public String updatedBy() {
        return getUserNickname(getUpdatedBy());
    }

    /**
     * 获取操作员ID
     * @param uid
     * @return
     */
    private String getUidString(String uid) {
        if (Strings.isNotBlank(uid)) {
            return uid;
        }
        try {
            return SecurityUtil.getUserId();
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取操作员姓名
     * @param nickname
     * @return
     */
    private String getUserNickname(String nickname) {
        if (Strings.isNotBlank(nickname)) {
            return nickname;
        }
        try {
            return SecurityUtil.getUserNickname();
        } catch (Exception e) {
        }
        return "";
    }
}
