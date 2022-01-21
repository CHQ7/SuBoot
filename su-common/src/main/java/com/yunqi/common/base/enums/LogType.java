package com.yunqi.common.base.enums;

/**
 * 日志类型
 * Created by @author JsckChin on 2022/1/19
 */
public enum LogType {
    /**
     * 其它
     */
    OTHER("OTHER", "其它"),

    /**
     * 新增
     */
    INSERT("INSERT","新增"),

    /**
     * 修改
     */
    UPDATE("UPDATE", "修改"),

    /**
     * 删除
     */
    DELETE("DELETE", "删除"),

    /**
     * 排序
     */
    SORT("SORT", "排序"),

    /**
     * 清空数据
     */
    CLEAN("CLEAN", "清空数据"),

    /**
     * 状态
     */
    STATUS("STATUS", "状态"),

    /**
     * 执行
     */
    EXECUTE("EXECUTE", "执行"),

    /**
     * 授权
     */
    GRANT("GRANT", "授权"),

    /**
     * 导出
     */
    EXPORT("EXPORT", "导出"),

    /**
     * 导入
     */
    IMPORT("IMPORT", "导入"),

    /**
     * 强退
     */
    FORCE("FORCE", "强退"),

    /**
     * 生成代码
     */
    GENCODE("GENCODE", "生成代码"),


    ;

    private String type;
    private String message;


    LogType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
}
