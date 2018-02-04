package com.dbh.kafka.provider;

import com.dbh.kafka.beans.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 消息发送类
 *
 * @author dbh
 * @date 2018/02/04
 */
@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    /**
     * 消息发送方法
     * topic在java程序中不需要提前在Kfaka中设置，
     * 因为会在发送的时候自动创建你设置的topic
     */
    public void send(){
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++ message = {}", gson.toJson(message));
        kafkaTemplate.send("dbh", gson.toJson(message));
    }

}
