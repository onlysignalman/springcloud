package com.dbh.kafka;

import com.dbh.kafka.provider.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * KafkaApplication class
 *
 * @author dbh
 * @date 2018/02/04
 */
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contenxt = SpringApplication.run(KafkaApplication.class, args);

		KafkaSender sender = contenxt.getBean(KafkaSender.class);

		for(int i = 0; i < 3; i++){
			//调用消息发送类中的发送发放
			sender.send();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
