package com.yunqi.starter.common.lang;


import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

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

    /**
     * <p>
     *     用运行时异常包裹抛出对象，如果抛出对象本身就是运行时异常，则直接返回。
     * </p>
     * 如果是 InvocationTargetException，那么将其剥离，只包裹其 TargetException
     * @param e 抛出对象
     * @return  运行时异常
     */
    public static RuntimeException wrapThrow(Throwable e) {
        if (e instanceof RuntimeException)
            return (RuntimeException) e;
        if (e instanceof InvocationTargetException)
            return wrapThrow(((InvocationTargetException) e).getTargetException());
        return new RuntimeException(e);
    }

}
