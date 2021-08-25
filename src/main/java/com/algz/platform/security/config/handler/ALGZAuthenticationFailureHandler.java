package com.algz.platform.security.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.algz.platform.utility.JsonResult;
import com.algz.platform.utility.JsonResult.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登录失败处理器主要用来对登录失败的场景（密码错误、账号锁定等…）做统一处理并返回给前台统一的json返回体。还记得我们创建用户表的时候创建了账号过期、密码过期、账号锁定之类的字段吗，这里就可以派上用场了.
 * 
 * 登录失败处理逻辑 
 * @author algz
 *
 */
@Component
public class ALGZAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//返回json数据
//		{
//		      status: 'error',
//		      type,
//		      currentAuthority: 'guest',
//		    }
        JsonResult<?> result = null;
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result =new JsonResult<Object>(false,ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof BadCredentialsException) {
            //密码错误
            result = new JsonResult<Object>(false,ResultCode.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = new JsonResult<Object>(false,ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result =new JsonResult<Object>(false,ResultCode.USER_ACCOUNT_DISABLE);
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = new JsonResult<Object>(false,ResultCode.USER_ACCOUNT_LOCKED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = new JsonResult<Object>(false,ResultCode.USER_ACCOUNT_NOT_EXIST);
        }else{
            //其他错误
            result = new JsonResult<Object>(false,ResultCode.COMMON_FAIL);
        }
       //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");

        result.setCurrentAuthority("guest");
        result.setType("account");
//        {"status":"error","currentAuthority":null,"success":false,"statusCode":2003,"statusMsg":"密码错误","data":null}  
//        {"status":"error","type":"account","currentAuthority":"guest"}
        String str="";
        //str="{\"status\":\"error\",\"type\":\"account\",\"currentAuthority\":\"guest\"}";
        //str="{\"status\":\"error\",\"type\":\"account\",\"currentAuthority\":\"guest\",\"success\":true,\"statusCode\":2003,\"statusMsg\":\"密码错误\",\"data\":null}";
        //response.getWriter().write(str);
        //塞到HttpServletResponse中返回给前台
        //{"status":"error","type":"account","currentAuthority":"guest"}
        //{"status":"error","type":"account","currentAuthority":"guest","success":false,"statusCode":2003,"statusMsg":"密码错误","data":null}
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
	}

}
