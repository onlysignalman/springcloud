package com.dbh.rabbitmq;

import com.dbh.rabbitmq.client.RabbitMQClient;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	/*@Bean
	public Queue testQueue(){
		return new Queue("dbh");
	}*/

	/*@Bean
	public Queue queue(){
		return new Queue("rpc-queue-dbh");
	}*/

	/**
	 * 创建交换机
	 * @return
	 */
	/*@Bean
	public TopicExchange exchange(){
		return new TopicExchange("rpc-exchange-dbh");
	}*/

	/**
	 * 队列和交换机绑定规则
	 * rpc-dbh是routingkey
	 * RabbitMQ将会根据这个参数去寻找有没有匹配此规则的队列
	 * 如果有，则会把消息发送给它，如果不止有一个，则会把消息分发给所有匹配的队列。
	 * @return
	 */
	/*@Bean
	public Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("rpc-dbh");
	}*/

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

	/*@PostConstruct
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
	}*/


	/*@PostConstruct
	public void init(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < 1000; i++){
			rabbitMQClient.send("dbh ------- send " + i);
		}
		stopWatch.stop();
		System.out.println("总耗时：" + stopWatch.getTotalTimeMillis());
	}*/

	@Bean(name = "queue")
	public Queue queue(){
		return new Queue("rpc-queue");
	}

	@Bean(name = "queue2")
	public Queue queue2(){
		return new Queue("rpc-queue2");
	}

	@Bean(name = "queue3")
	public Queue queue3(){
		return new Queue("rpc-queue3");
	}

	@Bean
	public FanoutExchange exchange(){
		return new FanoutExchange("fanout-exchange");
	}

	@Bean
	public Binding binding(@Qualifier("queue") Queue queue, FanoutExchange exchange){
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Binding binding2(@Qualifier("queue2") Queue queue, FanoutExchange exchange){
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Binding binding3(@Qualifier("queue3") Queue queue, FanoutExchange exchange){
		return BindingBuilder.bind(queue).to(exchange);
	}

	@PostConstruct
	public void init(){
		rabbitMQClient.send("dbh --------- send");
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
}
