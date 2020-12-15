package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.algz.platform.utility.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 同样的登出也要将登出成功时结果返回给前台，并且登出之后进行将cookie失效或删除.
 * @Description: 登出成功处理逻辑.
 * @author algz
 *
 */
@Component
public class ALGZLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        JsonResult<?> result =new JsonResult<Object>(true);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));

	}

}
