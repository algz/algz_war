package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

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
        if(savedRequest==null) {
        	//方法1.如果没有上个请求,则返回成功标识
    		JsonResult<?> result =new JsonResult<Object>(true);
    		response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
            //方法2.如果没有上个请求,则跳转到主页面.相对路径,默认为/algz.
            //        	response.sendRedirect("");
        }else {
            String url = savedRequest.getRedirectUrl();
            response.sendRedirect(url);
        }


	}


}
