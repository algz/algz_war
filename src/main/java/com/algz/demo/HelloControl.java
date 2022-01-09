package com.algz.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.LifecycleListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.algz.amqp.ProducerSend;
import com.algz.platform.common.crypto.RSAEncryption;
//import com.algz.amqp.ProducerSend;
import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.security.config.AUserDetailsService;
import com.algz.redis.MessageConsumerService;
import com.algz.redis.MessageEntity;
import com.algz.redis.MessageProducerService;
import com.algz.websocket.javax.WebSocketServer;

@RequestMapping("/demo")
@RestController
public class HelloControl {

	@Autowired
  private MessageProducerService producer;

  @Autowired
  private MessageConsumerService consumer;
	
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private WebSocketServer webSocketServer;
	
	@Autowired
	private ProducerSend mqSend; // 使用RabbitTemplate,这提供了接收/发送等等方法

	@Autowired
	private AUserRepository udao;

	@RequestMapping("/index")
	public ModelAndView getIndex() {
		String[] arr=RSAEncryption.genKeyPair();
		String c="Lr3S96hFRbL/ArhwXLb0M8s2rPziv90V32pq4iUd97417tuQDuBBi0nGgEmjb464AFLGndygolCxfSEXR3I8qqUj4UULVTR0KiQXL/8vp/aRMxm7sE7iyr32bJnSl3Gs2hEXHM+yTTm1z7rmU6JuMzal24Bf5pI8XXFgiUGNDus=";
		//公钥
		String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1icuM7QxgJlCDEo8siRqBOe2fcnCvquN6o/Gd/MtEUf8AvPRMvKKxtBTyB9pUyP8N9/6qNR3UMombSDIDJAHOP/YUPSOCxASwQyVeoDLzPy8XFeHLCZCm3LVfZqyLRaQG05pSIU2f65XcoANvkPiGclvrHsufHtcrU2FXkbGDPQIDAQAB";
		//私钥
		String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALWJy4ztDGAmUIMSjyyJGoE57Z9ycK+q43qj8Z38y0RR/wC89Ey8orG0FPIH2lTI/w33/qo1HdQyiZtIMgMkAc4/9hQ9I4LEBLBDJV6gMvM/LxcV4csJkKbctV9mrItFpAbTmlIhTZ/rldygA2+Q+IZyW+sey58e1ytTYVeRsYM9AgMBAAECgYB1tXk0UWfa2D0QH/KRz/xNmkAHSO7kGIwTM0YxSH6MOBZ+ZgaJ0y4yxy9ll83rd8BZUFniBzrmIjazZuZ2vtwjxgtbdzJcOUuXVx9UQ3Q9VccFoenoXA2ev39/BdLuDjFTabDjsvIFAnr6vfAFdeVZAfrAO0Hjq6ngkQAxtzmOQQJBAN3ajRpcdes8v/cbf/+dEC5ulX/lRuYs8REc1e9IOYI9NqyGVXm4Xl/JIQTJ7xEz6LUqU120joKl/MuEV8cMuWUCQQDRer148HQFvG2iz0e8Zy5k6RDdNZn+s34At6TMC/dwY67LqY1G4aImaNRG1Jv4hA2wKI4RJ0PO2QsvuNYnkXD5AkEAkODecK6c2wgLHKY6yoSZQpk4zBLAYCRNMS2xj9eMpr6u66+2NgzupILGK0hr9MH/X//J/VGiFzOqiIVehUCL+QJAdO3UcXuW59TERgr5+fqGdSF7nl7bNMcwYGoh/Rv45K1f1qMwDw7HmjUNuPPYlCHyGhCpLH4EZaPVkK/WKoQO0QJAG8SYMoX/aOCZr5HaSV5++d5u96HALrxSSd6epay5pzXm++FIQ4XqWJx+mmg3KfplU1C8R0J0XtpwAL+sotcaJQ==";
		String dc=RSAEncryption.decrypt(c, privateKey);
		System.out.print(dc);

		consumer.start();
		System.out.println(redisTemplate.opsForValue().get("test1"));
		 ModelAndView mv=new ModelAndView();
	        mv.setViewName("test");
	        mv.addObject("name", "liyafei");
//	        user.setAge(20);
//	        user.setName("wangwu");
//	        mv.addObject("user", user);
	        
	        //设置返回的数据为json类型，也可以不设置，返回对象
	        //mv.setView(new MappingJackson2JsonView());
	        return mv; 
	}
	
	@RequestMapping("/hello")
	public List<AUser> index(String str) {
		producer.sendMeassage(new MessageEntity("1", "aaaa1"));
		redisTemplate.opsForValue().set("test1", "val1");
		webSocketServer.sendMsgToUser("1", "test,hello!");
		
		return udao.findAll();
	}

	@RequestMapping("/currentuser")
	public List<AUser> getCurrentUser(HttpServletRequest request) {
		SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		// 方式1(推荐)，ud 其实是AUser类型（实现UserDetails接口）
		UserDetails ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
		System.out.println(String.format("username:%s,password:%s", ud.getUsername(), ud.getPassword()));

		// 方式2.没有方式获得的数据多。
		Authentication auth = securityContext.getAuthentication();
		if (auth != null) {
			System.out.println("Username:" + auth.getName()); // 登录名
			System.out.println("Credentials:" + auth.getCredentials()); // 登录密码，未加密的

			WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
			System.out.println("RemoteAddress:" + details.getRemoteAddress()); // 获得访问地址
			System.out.println("SessionId:" + details.getSessionId()); // 获得sessionid

			// 获得当前用户所拥有的权限
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				System.out.println("Authority:" + grantedAuthority.getAuthority());
			}
		}

		return udao.findAll();
	}

	@RequestMapping("/amqp")
	public String amqp(String str) {
		mqSend.sendMsgByTopics();
		return "";
	}

	//@Autowired
	private AUserDetailsService userSecurityService;
	/**
	 * 手动自定义登录，没有采用过滤器。（暂时不使用）
	 * AUserDetailsService 类需添加@Service注解，才能使用。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/sd")
	public void shoudongdenglu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		// 根据用户名username加载userDetails
		UserDetails userDetails = userSecurityService.loadUserByUsername("admin");

		// 根据userDetails构建新的Authentication,这里使用了
		// PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken
		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());
		// 设置authentication中details
		authentication.setDetails(new WebAuthenticationDetails(request));
		// 存放authentication到SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		// 在session中存放security context,方便同一个session中控制用户的其他操作
		session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

		response.sendRedirect("/securityexample/test");

		// myAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);

//	        return;
		// 方法2
//	        UsernamePasswordAuthenticationToken authReq=new UsernamePasswordAuthenticationToken("", "");
//	        Authentication auth=

	}
}
