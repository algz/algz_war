package com.algz.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
//    @Value("${spring.rabbitmq.host}")
//    private String host;
// 
//    @Value("${spring.rabbitmq.port}")
//    private int port;
// 
//    @Value("${spring.rabbitmq.username}")
//    private String username;
// 
//    @Value("${spring.rabbitmq.password}")
//    private String password;
	
	public static final String QUEUE_EMAIL = "queue_dynamic";// email队列
//	public static final String QUEUE_SMS = "queue_sms";// sms队列
	public static final String EXCHANGE_NAME = "topic.exchange";// topics类型交换机
	public static final String ROUTINGKEY_EMAIL = "topic.#.dynamic.#"; //采用通配符, ①*（星号）仅代表一个单词 ②#（井号）代表任意个单词
//	public static final String ROUTINGKEY_SMS = "topic.#.sms.#";

	// 声明交换机
	@Bean(EXCHANGE_NAME)
	public Exchange exchange() {
		// durable(true) 持久化，mq重启之后交换机还在
		return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
	}

	/**
	 * 声明email队列 
	 * new Queue(QUEUE_EMAIL,true,false,false) 
	 * durable="true" 持久化
	 * rabbitmq重启的时候不需要创建新的队列 
	 * auto-delete 表示消息队列没有在使用时将被自动删除 默认是false exclusive
	 * 表示该消息队列是否只在当前connection生效,默认是false
	 */
	@Bean(QUEUE_EMAIL)
	public Queue emailQueue() {
		//this(name, true, false, false);
		//Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
		return new Queue(QUEUE_EMAIL);
	}

	/**
	 * 声明sms队列
	 * 
	 * @return
	 */
//	@Bean(QUEUE_SMS)
//	public Queue smsQueue() {
//		return new Queue(QUEUE_SMS);
//	}

	// ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
	@Bean
	public Binding bindingEmail(@Qualifier(QUEUE_EMAIL) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
	}

//	// ROUTINGKEY_SMS队列绑定交换机，指定routingKey
//	@Bean
//	public Binding bindingSMS(@Qualifier(QUEUE_SMS) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
//	}

}
