package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import com.algz.platform.utility.JsonResult;
import com.algz.platform.utility.JsonResult.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 同样的，当账号异地登录导致被挤下线时也要返回给前端json格式的数据，比如提示"账号下线"、"您的账号在异地登录，是否是您自己操作"或者"您的账号在异地登录,可能由于密码泄露，建议修改密码"等。这时就要实现SessionInformationExpiredStrategy（会话信息过期策略）来自定义会话过期时的处理逻辑。
 * 
 * @Description: 会话信息过期策略
 * @author algz
 *
 */
@Component
public class ALGZSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		JsonResult<?> result = new JsonResult<Object>(false,ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
		HttpServletResponse httpServletResponse = event.getResponse();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
	}

}
