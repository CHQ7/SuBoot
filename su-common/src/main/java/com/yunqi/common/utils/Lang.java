package com.yunqi.common.utils;

import org.nutz.lang.stream.StringOutputStream;

import java.io.PrintStream;

/**
 * <p>
 *     这些帮助函数让 Java 的某些常用功能变得更简单
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public abstract class Lang {

    /**
     * <p>
     *     将一个抛出对象的异常堆栈，显示成一个字符串
     * </p>
     *
     * @param e 抛出对象
     * @return 异常堆栈文本
     */
    public static String getStackTrace(Throwable e) {
        StringBuilder sb = new StringBuilder();
        StringOutputStream sbo = new StringOutputStream(sb);
        PrintStream ps = new PrintStream(sbo);
        e.printStackTrace(ps);
        ps.flush();
        return sbo.getStringBuilder().toString();
    }
}
