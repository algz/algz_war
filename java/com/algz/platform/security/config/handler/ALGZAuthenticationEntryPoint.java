package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.algz.platform.utility.JsonResult;
import com.algz.platform.utility.JsonResult.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 注入到SecurityConfig后,spring security不再跳转html,即前后端分离.
 * 在前后端分离的情况下（比如前台使用VUE或JQ等）我们需要的是在前台接收到"用户未登录"的提示信息，所以我们接下来要做的就是屏蔽重定向的登录页面，并返回统一的json格式的返回体。而实现这一功能的核心就是实现AuthenticationEntryPoint并在WebSecurityConfig中注入，然后在configure(HttpSecurity http)方法中。
 * 
 * AuthenticationEntryPoint主要是用来处理匿名用户访问无权限资源时的异常（即未登录，或者登录状态过期失效）
 * 
 * @Description: 匿名用户访问无权限资源时的异常
 * @author algz
 *
 */
@Component
public class ALGZAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		  
	        JsonResult<Object> result = new JsonResult<Object>(false,ResultCode.USER_NOT_LOGIN);
	      	response.setContentType("text/json;charset=utf-8");
	      	ObjectMapper mapper = new ObjectMapper();
	        response.getWriter().write(mapper.writeValueAsString(result));
	        

	}

}
