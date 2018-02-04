package com.dbh.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消息接收类
 *
 * @author dbh
 * @date 2018/02/04
 */
@Component
@Slf4j
public class KafkaReceiver {

    /**
     * 直接用 @KafkaListener 注解即可，并在监听中设置监听的 topic
     *
     * @param record
     */
    @KafkaListener(topics = {"dbh"})
    public void listen(ConsumerRecord<?, ?> record){
        Optional<?> kfakaMessage = Optional.ofNullable(record.value());

        if(kfakaMessage.isPresent()){
            Object message = kfakaMessage.get();

            log.info("--------------- record =" + record);
            log.info("---------------- message =" + message);

        }
    }
}
