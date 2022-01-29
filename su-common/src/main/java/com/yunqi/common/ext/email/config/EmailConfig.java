package com.yunqi.common.ext.email.config;

/**
 * <p>
 *     邮件参数配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class EmailConfig {
    /** 邮件服务器域名 */
    public static String HOST_NAME = "smtp.qiye.aliyun.com";

    /** 邮件服务器smtp协议端口 */
    public static String SMTP_PORT = "4655";

    /** 邮件账号 */
    public static String USER_NAME = "notice@fir7.com";

    /** 邮件密码 */
    public static String USER_PASSWORD = "Ng1259900";

    /** 是否启用SSL */
    public static boolean SSL = true;

    /** 邮箱昵称 */
    public static String FROM_NAME = "notice@fir7.com";

    /** 编码 */
    public static String CHARSET = "UTF-8";
}
