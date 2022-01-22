package com.yunqi.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 钉钉工具类
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
public class DingtalkUtil {

    /**
     * 钉钉API地址
     */
    private static final String DOMAIN_URL = "https://oapi.dingtalk.com";

    private static final String appkey = "dingwrbx15sdjwzvanko";
    private static final String appsecret = "4ahdGBVeGsRY8gVLeHe_TKLhyO5ej_-GqilipmFechudQNlRQxTBD1R0blXXqf48";


    /**
     * 获取钉钉凭证
     * @return
     */
    public static NutMap getTicket(){
        // 请求链接
        NutMap map = HttpUtil.get(DOMAIN_URL + "/gettoken?appkey=" + appkey + "&appsecret=" + appsecret);
        exp(map);
        return map;
    }




    /**
     * 获取钉钉凭证
     * @return
     */
    public static String getToken(){
        return String.valueOf(TokenSingleton.getInstance().getAccessToken().get("access_token"));
    }


    /**
     * 推送消息到企业群 > 支持钉钉群类型:内部群、全员群、部门群
     * @param chatId 群会话的ID
     * @param title 消息标题
     * @param text 消息内容
     */
    public static void chatSend(String chatId, String title,String text) {
        NutMap map = HttpUtil.post(DOMAIN_URL + "/chat/send?access_token=" + getToken(), chatMarkdownData( chatId,title,text));
        exp(map);
    }

    /**
     * 验证钉钉接口是否异常
     * @param map
     */
    private static void exp(NutMap map){
        // 验证请求数据返回的结果是否成功
        if(map.getInt("errcode") != 0){
            throw new RuntimeException("请求钉钉接口失败,异常代码:" + map.getInt("errcode")+ ",原因:" + map.getString("errmsg"));
        }
    }

    /**
     * 钉钉群 markdown类型
     * @param chatId 钉钉群ID
     * @param title 标题
     * @param text 内容
     * @return
     */
    private static String chatMarkdownData(String chatId, String title,String text) {
        NutMap map = new NutMap();
        //消息内容
        NutMap contentMap = new NutMap();
        contentMap.addv("title", title);
        contentMap.addv("text", text);

        NutMap reqMap = new NutMap();
        reqMap.put("msgtype", "markdown");
        reqMap.put("markdown", contentMap);

        map.addv("chatid", chatId);
        map.addv("msg", reqMap);

        return Json.toJson(map);
    }


    /**
     * 钉钉获取部门列表接口
     * @param dept_id 部门ID
     * @return
     */
    public static List<NutMap> getDepartment(String dept_id){
        NutMap data = new NutMap();
        if(Strings.isNotBlank(dept_id)){
            data.put("dept_id", dept_id);
        }
        // 请求链接
        NutMap map = HttpUtil.post(DOMAIN_URL + "/topapi/v2/department/listsub?access_token=" + getToken(), Json.toJson(data));
        // 组装返回列表集合
        List<NutMap> nutMaps = new ArrayList<>();
        // 获取部门数据列表
        List<NutMap> list= map.getList("result", NutMap.class);
        if(list.size() != 0) {
            for (NutMap m: list) {
                NutMap nm = new NutMap();
                nm.put("parent_id",  m.getString("parent_id"));
                nm.put("dept_id",  m.getString("dept_id"));
                nm.put("name",  m.getString("name"));
                nutMaps.add(nm);
            }
        }
        return nutMaps;
    }

    /**
     * 获取所有部门列表
     * @return String
     */
    public static List<NutMap> getDepartment() {
        List<NutMap> returnList = new ArrayList<>();
        for (NutMap t : getDepartment("")) {
            recursionFn(t);
            returnList.add(t);
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param t
     */
    private static void recursionFn(NutMap t) {
        // 得到子节点列表
        List<NutMap> childList = getDepartment(t.getString("dept_id"));
        t.put("children", childList);
        // 判断是否有子节点
        for (NutMap tChild : childList) {
            // 判断是否有子节点
            if (getDepartment(tChild.getString("dept_id")).size() > 0) {
                recursionFn(tChild);
            }
        }
    }


    /**
     * 获取部门用户userid列表
     * @param dept_id 部门ID
     * @return
     */
    public static List<String> getUserIds(String dept_id){
        NutMap data = new NutMap();
        data.put("dept_id", dept_id);
        List<String> stringList = new ArrayList<>();
        // 请求链接
        NutMap map = HttpUtil.post(DOMAIN_URL + "/topapi/user/listid?access_token=" + getToken(), Json.toJson(data));
        System.out.println("打印结果:" + Json.toJson(map));
        if(map.getInt("errcode") == 0){
            stringList.addAll(Json.fromJson(NutMap.class, map.getString("result")).getList("userid_list", String.class));
        }
        return stringList;
    }

}
