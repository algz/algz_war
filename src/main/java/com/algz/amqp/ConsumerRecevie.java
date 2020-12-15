//package com.algz.amqp;
//
//
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ConsumerRecevie {
//
//	/**
//	 *   监听邮件队列
//	 *  @param RabbitListener:声明这个方法是一个消费者方法
//	 *  @param bindings：指定绑定关系，可以有多个。值是@QueueBinding的数组。@QueueBinding包含下面属性：
//		@param value：这个消费者关联的队列。值是@Queue，代表一个队列.
//		@param exchange：队列所绑定的交换机，值是@Exchange类型.
//		@param key：队列和交换机绑定的RoutingKey，可指定多个.
//	 */
//	@RabbitListener(bindings = @QueueBinding(
//			value =@Queue(value = "queue_email", durable = "true"), 
//			exchange = @Exchange(value = "topic.exchange", 
//			ignoreDeclarationExceptions = "true", 
//			type = ExchangeTypes.TOPIC), 
//			key = {"topic.#.email.#", "email.*" }))
//	public void rece_email(String msg) {
//		System.out.println(" [邮件服务] received : " + msg + "!");
//	}
//
//	// 监听短信队列
//	@RabbitListener(bindings = @QueueBinding(
//			value = @Queue(value = "queue_sms", durable = "true"), 
//			exchange = @Exchange(value = "topic.exchange", 
//			ignoreDeclarationExceptions = "true", 
//			type = ExchangeTypes.TOPIC), 
//			key = {"topic.#.sms.#" }))
//	public void rece_sms(String msg) {
//		System.out.println(" [短信服务] received : " + msg + "!");
//	}
//}
