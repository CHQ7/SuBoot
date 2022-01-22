package com.yunqi.common.utils;

import org.nutz.http.Request;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;

/**
 * Created by @author JsckChin on 2022/1/22
 */
public class HttpUtil {

    /**
     * GET请求
     * @param url 请求地址
     * @return NutMap
     */
    public static NutMap get(String url){
        // 发起网络请求
        Request req = Request.create(url, Request.METHOD.GET);

        // 设置Json请求格式
        req.getHeader().set("Content-Type", "application/json");

        // 获取请求数据
        Response resp = Sender.create(req).setTimeout(6000).send();

        // 解析请求数据成Map格式
        return Json.fromJson(NutMap.class, resp.getContent());
    }

    /**
     * POST请求
     * @param url 请求地址
     * @param data 请求数据
     * @return NutMap
     */
    public static NutMap post(String url, String data){
        // 发起网络请求
        Request req = Request.create(url, Request.METHOD.POST);

        // 设置Json请求格式
        req.getHeader().set("Content-Type", "application/json");

        // 设置请求Json数据
        req.setData(data);

        // 获取请求数据
        Response resp = Sender.create(req).setTimeout(6000).send();

        // 解析请求数据成Map格式
        return Json.fromJson(NutMap.class, resp.getContent());
    }
}
