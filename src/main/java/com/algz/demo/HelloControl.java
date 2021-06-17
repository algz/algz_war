package com.algz.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.LifecycleListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
	
	@RequestMapping("/currentuser")
	public List<AUser>  getCurrentUser(HttpServletRequest request) {
		SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
		
		//方式1(推荐)，ud 其实是AUser类型（实现UserDetails接口）
		UserDetails ud=(UserDetails)securityContext.getAuthentication().getPrincipal();
		System.out.println(String.format("username:%s,password:%s",ud.getUsername(),ud.getPassword()));
		
		//方式2.没有方式获得的数据多。
		Authentication auth=securityContext.getAuthentication(); 
		if(auth!=null) {
			System.out.println("Username:"+ auth.getName());  // 登录名 
			System.out.println("Credentials:"+auth.getCredentials()); // 登录密码，未加密的  
			
			WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
			System.out.println("RemoteAddress:" + details.getRemoteAddress());  // 获得访问地址
			System.out.println("SessionId:" + details.getSessionId());  // 获得sessionid 
			
			// 获得当前用户所拥有的权限  
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();  
			for (GrantedAuthority grantedAuthority : authorities) {  
			 System.out.println("Authority:" + grantedAuthority.getAuthority());  
			}  
		}

		
		return udao.findAll();
	}
	
	@RequestMapping("/amqp")
	public String  amqp(String str) {
		mqSend.sendMsgByTopics();
		return "";
	}
}
