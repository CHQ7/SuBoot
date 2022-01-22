package com.yunqi.common.listener;

import com.rabbitmq.client.Channel;
import com.yunqi.common.config.RabbitConfiguration;
import com.yunqi.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 *     消费端
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Component
@RabbitListener(queues= RabbitConfiguration.QUEUE_NAME)
public class DirectReceiver {

    @Resource
    RedisUtil redisUtil;

    @RabbitHandler
    public void process(NutMap msg, Message message, Channel channel) throws IOException {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        String messageId = msg.getString("messageId");
        log.info("----收到{}消息，开始消费-----", messageId);
        // fix:重复消费

        // 存在
        if(redisUtil.hasKey(messageId)){
            log.info("存在重复消费>" + messageId);
            log.info("打印重复数据 : " + Json.toJson(msg));
            channel.basicAck(deliveryTag, false);
            return;
        }
        redisUtil.set(messageId , Json.toJson(msg));
        // 通知 MQ 消息已被成功消费,可以ACK了
        channel.basicAck(deliveryTag, false);
        log.info("-------{}消费完成--------", messageId);
    }
}
