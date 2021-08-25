package com.algz.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.LifecycleListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
//import com.algz.amqp.ProducerSend;
import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.security.config.AUserDetailsService;

@RequestMapping("/demo")
@RestController
public class HelloControl {

	@Autowired
	private ProducerSend mqSend; // 使用RabbitTemplate,这提供了接收/发送等等方法

	@Autowired
	private AUserRepository udao;

	@RequestMapping("index")
	public ModelAndView getIndex() {
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
