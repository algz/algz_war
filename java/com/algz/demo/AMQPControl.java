//package com.algz.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.algz.amqp.ConsumerRecevie;
//import com.algz.amqp.ProducerSend;
//
//@RestController
//@RequestMapping("/amqp")
//public class AMQPControl {
//
//	@Autowired
//	private ProducerSend send;
//	
//	@Autowired
//	private ConsumerRecevie recevie;
//	
//	@RequestMapping("/send")
//	public String Send() {
//		send.sendMsgByTopics();
//		return "";
//	}
//	
//	@RequestMapping("/recevie")
//	public String Recevie() {
//		//recevie
//		return "";
//	}
//}
