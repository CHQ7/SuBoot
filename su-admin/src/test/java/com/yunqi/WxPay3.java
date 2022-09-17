package com.yunqi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.json.Json;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created by @author JsckChin on 2022/1/30
 */
@Slf4j
@SpringBootTest
class WxPay3 {


    @Test
    public void test() throws Exception {
        // 商户号
        String mchId ="1515176431";

        // V3API证书序列号
        String serialNo="5D64C9AD56352E8B6D9F625A1A3CD9489B378D76";

        // V3Key证书文件路径
        String keyPath="F:\\apiclient_key.pem";


        NutMap amount = NutMap.NEW().addv("total", 1);
        NutMap payer = NutMap.NEW().addv("openid", "ovD3M5OAolRoiapoxSW1cySMXTv8");

        NutMap data = new NutMap();
        data.addv("mchid", mchId);
        data.addv("out_trade_no", R.UU32());
        data.addv("appid", "wxed67d757e83831e7");
        data.addv("description", "商品");
        data.addv("notify_url", "https://www.weixin.qq.com/wxpay/pay.php");
        data.addv("amount", amount);
        data.addv("payer", payer);


        WxPay3Response response = WxPay3Api.v3_order_jsapi(mchId, serialNo, keyPath, Json.toJson(data));
        log.warn("打印数据 ->> ");

        log.warn(Json.toJson(response.getBody()));
    }


}
