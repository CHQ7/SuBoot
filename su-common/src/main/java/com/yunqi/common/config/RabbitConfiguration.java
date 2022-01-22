package com.yunqi.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     Rabbit配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Configuration
public class RabbitConfiguration {

    // 队列名称
    public static final String QUEUE_NAME = "DirectQueue";
    // 交换器名称
    public static final String EXCHANGE  = "DirectExchange";
    // 路由键名称
    public static final String ROUTEKEY  = "DirectRoutekey";


    /**
     * <p>
     *     创建队列
     * </p>
     * @return 新的消息队列
     */
    @Bean
    public Queue queue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接端库后，是否自动删除队列
        boolean autoDelete = false;
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(QUEUE_NAME, durable, exclusive, autoDelete);
    }

    /**
     * <p>
     *     创建Direct交换器
     * </p>
     * @return 新的Direct交换器
     */
    @Bean
    public DirectExchange exchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(EXCHANGE,durable,autoDelete);
    }


    /**
     * <p>
     *     绑定交换器和队列通过路由键
     * </p>
     * @return  绑定生成器
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTEKEY);
    }
}
