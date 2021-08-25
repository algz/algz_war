package com.algz.platform.security.config.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.utility.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.nashorn.internal.runtime.regexp.joni.Option;

/**
 * @Description: 登录成功处理逻辑
 * @author algz
 *
 */
@Component
public class ALGZAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		//1.登陆成功不跳转,不记住上一个请求的URI
//		JsonResult<?> result =new JsonResult<Object>(true);
//		response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        
        //2.登陆成功跳转到上一个请求的URI.
        response.setContentType("application/json;charset=utf-8");
        RequestCache cache = new HttpSessionRequestCache();
        SavedRequest savedRequest = cache.getRequest(request, response);
        
    	//方式1.前端是AJAX发送的请求。（不能重定向，否则报跨域资源共享异常。）
//		{status: 'ok',type,currentAuthority: 'admin',}
        //如果没有上个请求,则返回成功标识
    	List<String> auList=new ArrayList<String>();
    	AUser currentUser = (AUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currentUser.getAuthorities().forEach(x->{
			auList.add(x.getAuthority());
		});
    	JsonResult<?> result =new JsonResult<Object>(true);
    	result.setCurrentAuthority(String.join(",", auList));
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(new ObjectMapper().writeValueAsString(result));
		
		//方式2。前端不是AJAX发送的请求。
//        if(savedRequest==null) {
//            //如果没有上个请求,则跳转到主页面.相对路径,默认为/algz.
//            response.sendRedirect("");
//        }else {     
//            String url = savedRequest.getRedirectUrl();
//            response.sendRedirect(url);
//        }


	}


}
