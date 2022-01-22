package com.yunqi.common.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import org.nutz.lang.Strings;

/**
 * <p>
 *     安全认证
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class SecurityUtil {

    /**
     * 获取当前会话账号id, 如果未登录，则抛出异常
     * @return 账号id
     */
    public static String getUserId() {
        return Strings.sNull(StpUtil.getLoginId());
    }

    /**
     * 获取当前会话的Session
     * @return Session对象
     */
    public static SaSession getSession() {
        return StpUtil.getSession(true);
    }

    public static String getUserName() {
        return Strings.sNull(getSession().get("username"));
    }

    public static String setUserName(String username) {
        return Strings.sNull(getSession().set("username", username));
    }

    public static String getUserNickname() {
        return Strings.sNull(getSession().get("nickname"));
    }

    public static String setUserNickname(String nickname) {
        return Strings.sNull(getSession().set("nickname", nickname));
    }
}
