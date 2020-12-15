package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.algz.platform.utility.JsonResult;
import com.algz.platform.utility.JsonResult.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: 权限拒绝处理逻辑
 * @author algz
 *
 */
@Component
public class ALGZAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JsonResult<Object> result = new JsonResult<Object>(false,ResultCode.NO_PERMISSION);
        result.setStatusMsg(accessDeniedException.getMessage());
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
		
	}

}
