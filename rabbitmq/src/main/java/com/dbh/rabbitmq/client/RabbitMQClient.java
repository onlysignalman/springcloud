package com.dbh.rabbitmq.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * dbh 这个是路由规则（routingKey）
     * 它的值表明将消息发送到指定队列dbh中去
     * @param message
     */
    public void send(String message) {
        rabbitTemplate.convertAndSend("dbh", message);
    }
}
