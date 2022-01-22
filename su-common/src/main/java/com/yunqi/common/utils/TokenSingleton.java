package com.yunqi.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *      accessToken单例设计模式 缓存
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class TokenSingleton {

    /**
     * 缓存accessToken 的Map,map中包含 一个accessToken 和 缓存的时间戳
     */
    private Map<String, String> map = new HashMap<String,String>();

    private TokenSingleton() {

    }

    private static TokenSingleton single = null;


    /**
     * 普通懒汉式
     * 静态工厂方法
     * @return
     */
    public static TokenSingleton getInstance() {
        if (single == null) {
            single = new TokenSingleton();
        }
        return single;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static TokenSingleton getSingle() {
        return single;
    }

    public static void setSingle(TokenSingleton single) {
        TokenSingleton.single = single;
    }

    /**
     * 获取 accessToken Jsapi_ticket 已加入缓存机制
     * @return
     */
    public Map<String,Object> getAccessToken() {

        // 返回结果
        Map<String,Object> result = new HashMap<>();

        // 获取单例
        TokenSingleton singleton = TokenSingleton.getInstance();

        // 获取单例map数据
        Map<String, String> map = singleton.getMap();

        //从缓存中拿数据
        String time = map.get("time");

        //从缓存中拿数据
        String accessToken = map.get("access_token");

        //从缓存中拿数据
        String expireIn = map.get("expireIn");

        // 获取系统时间戳单位为 毫秒
        long nowDate = System.currentTimeMillis();

        // 设置比过期时间少一些---> 7200s过期 设置超过7000s就刷新一次
        if (accessToken != null && time != null && nowDate - Long.parseLong(time) < 7000*1000) {
            //System.out.println("-----从缓存读取access_token："+accessToken);
            //从缓存中拿数据为返回结果赋值
            result.put("access_token", accessToken);
            result.put("expireIn", expireIn);
        } else {
            // 请求获取新的凭证
            Map<String,Object> info = DingtalkUtil.getTicket();
            // 重新获取并覆盖原先的值
            map.put("time", nowDate + "");
            map.put("access_token", String.valueOf(info.get("access_token")));
            map.put("expireIn", String.valueOf(info.get("expires_in")));
            // 为返回结果赋值
            result.put("access_token", info.get("access_token"));
            result.put("expireIn", info.get("expires_in"));
        }
        return result;
    }

}
