package com.yunqi.common.base;

import org.nutz.lang.util.NutMap;

/**
 * <p>
 *    全局参数配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class Globals {
    /** 项目路径 */
    public static String AppRoot = "";
    /** 项目目录 */
    public static String AppBase = "";
    /** 项目名称 */
    public static String AppName = "SnBoot";
    /** 项目域名 */
    public static String AppDomain = "http://127.0.0.1";
    /** 文件上传路径 */
    public static String AppUploadPath = "/attachment";

    /**
     * 系统自定义参数
     */
    public static NutMap CustomConfig = NutMap.NEW();

}
