package com.algz.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerSend {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMsgByTopics(String routingKey,String message) {
		/**
		 * 参数： 1、交换机名称(topic.exchange) 2、routingKey 3、消息内容
		 */
		// String message = "{taskid:'"+i+"',msg:'准备动力学仿真！'}";
		// exchange the name of the exchange,routingKey the routing key,object a message
		// to send.
		//routingKey="topic.sms.email.dynamic"
		rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, routingKey, message);
		System.out.println(" [x] Sent '" + message + "'");
//		}
	}

	public void sendMsgByTopics() {
		/**
		 * 参数： 1、交换机名称(topic.exchange) 2、routingKey 3、消息内容
		 */
		for (int i = 0; i < 15; i++) {
			String message = "{taskid:'" + i + "',msg:'准备动力学仿真！'}";
			// exchange the name of the exchange,routingKey the routing key,object a message
			// to send.
			rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "topic.sms.email.dynamic", message);
			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}
