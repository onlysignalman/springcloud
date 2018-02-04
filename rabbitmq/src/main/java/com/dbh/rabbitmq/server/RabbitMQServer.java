package com.dbh.rabbitmq.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQServer {

   /* @RabbitListener(queues = "dbh")
    public void receive(String message){
        System.out.println("收到的 message 是：" + message);
    }*/

    /*@RabbitListener(queues = "rpc-queue-dbh")
    public void receive(String message){
        System.out.println("----receive----" + message);
    }*/

    @RabbitListener(queues = "rpc-queue")
    public void receive(String message){
        System.out.println("----receive----" + message);
    }

    @RabbitListener(queues = "rpc-queue2")
    public void receive2(String message){
        System.out.println("----receive2----" + message);
    }

    @RabbitListener(queues = "rpc-queue3")
    public void receive3(String message){
        System.out.println("----receive3----" + message);
    }
}
