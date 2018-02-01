package com.dbh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @EnableBinding 该注解用来指定一个或者多个定义了@Input或@Output注解接口
 *                  以此实现对消通道Channel的绑定。
 */
@EnableBinding(Sink.class)
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    /**
     * 该注解主要定义在方法上，
     * 作用是将被修饰的方法注册为消息中间件上数据流的事件监听器，
     * 注解中的属性值对应了监听的消息通道名。
     * @param payload
     */
    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        logger.info("Receiver：" + payload);
    }
}
