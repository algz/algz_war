package com.algz.demo;

import java.util.List;

import org.apache.catalina.LifecycleListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.amqp.ProducerSend;
//import com.algz.amqp.ProducerSend;
import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;

@RequestMapping("/demo")
@RestController
public class HelloControl {

	 @Autowired
	 private ProducerSend mqSend;  //使用RabbitTemplate,这提供了接收/发送等等方法
	 
	
	@Autowired
	private AUserRepository udao;
	
	@RequestMapping("/hello")
	public List<AUser>  index(String str) {
		return udao.findAll();
	}
	
	@RequestMapping("/amqp")
	public String  amqp(String str) {
		mqSend.sendMsgByTopics();
		return "";
	}
}
