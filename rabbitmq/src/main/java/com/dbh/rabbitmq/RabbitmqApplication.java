package com.dbh.rabbitmq;

import com.dbh.rabbitmq.client.RabbitMQClient;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
@SpringBootApplication
public class RabbitmqApplication {

	@Autowired
	RabbitMQClient rabbitMQClient;

	/**
	 * 创建队列
	 * @return
	 */
	@Bean
	public Queue testQueue(){
		return new Queue("dbh");
	}

	/**
	 * @PostConstruct 修饰的方法会在构造函数之后执行
	 */
	/*@PostConstruct
	public void init(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < 10000; i++){
			rabbitMQClient.send("发送消息----dbh----");
		}
		stopWatch.stop();
		System.out.println("发送消息耗时：" + stopWatch.getTotalTimeMillis());
	}*/

	@PostConstruct
	public void init(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		int threads = 10;
		HandlerExecutePool handlerExecutePool = new HandlerExecutePool(threads);

		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(threads);

		for (int i = 0; i < threads; i++){
			handlerExecutePool.execute(() ->{
				try {
					start.await();
					for (int i1 = 0; i1 < 10000; i1++){
                        rabbitMQClient.send("发送消息----dbh----");
                    }
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					end.countDown();
				}
			});
		}
		start.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			handlerExecutePool.shutdown();
		}
		stopWatch.stop();
		System.out.println("发送消息耗时：" + stopWatch.getTotalTimeMillis());
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
}
