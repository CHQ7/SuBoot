package com.yunqi.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 请求上下文
 * Created by @author JsckChin on 2022/1/22
 */
public class HttpContextUtil {

    private HttpContextUtil(){

    }

    /**
     * 获取请求的req对象
     * @return 请求的req对象
     */
    public static HttpServletRequest getReq() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
