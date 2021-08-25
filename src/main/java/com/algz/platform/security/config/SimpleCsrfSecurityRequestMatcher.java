package com.algz.platform.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 这个类被用来自定义哪些请求是不需要进行拦截过滤的，如果配置csrf，所有http请求都被会CsrfFilter拦截。
 * 
 * @author algz
 *
 */
@Component
public class SimpleCsrfSecurityRequestMatcher implements RequestMatcher {

	/**
	 * 方法1 CSRF需要排除的url列表 "/upload","/webservice"
	 */
	private static HashSet<String> execludeUrls;// = new HashSet<>(Arrays.asList("/upload","/webservice"));

	@Value("${algz.csrf.execludeUrls:}") // 设置默认值为空字符串,否则没有在配置文件中定义会报异常.
	private void setExecludeUrls(String _execludeUrlStr) {
		// execludeUrlStr=execludeUrlStr==""?"/upload,/webservice":execludeUrlStr;
		execludeUrls = new HashSet<>(Arrays.asList(_execludeUrlStr.split(",")));
	}

	/**
	 * 方法2 CSRF需要排除的url的正则表达式
	 */
	private static RegexRequestMatcher unprotectedMatcher;

	@Value("${algz.csrf.unprotectedMatcher:}") // 设置默认值为空字符串,否则没有在配置文件中定义会报异常.
	private void setUnprotectedMatcher(String _unprotectedMatcher) {
//		unprotectedMatcher=^/rest/.*";
		unprotectedMatcher = new RegexRequestMatcher(_unprotectedMatcher, null);
	}

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS|POST)$");

//	private final HashSet<String> allowedMethods = new HashSet<>(
//			Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

	/**
	 * 返回,true拦截;false不拦截.
	 * 
	 */
	@Override
	public boolean matches(HttpServletRequest request) {
		// 正则模式="",与匹配都为true.
		if (unprotectedMatcher.matches(request)) {
			return false;
		}

		if (execludeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : execludeUrls) {
				if (servletPath.contains(url)) {
//	                    logger.debug("SimpleCsrfSecurityRequestMatcher排除的url:" + servletPath);
					return false;
				}
			}
		}

		// 前后端分离，判断是否为ajax请求，是则不拦截。
		boolean isAjaxRequest = false;
		if (!(request.getHeader("Content-Type") == null)
				&& request.getHeader("Content-Type").contains("application/json")) {
			isAjaxRequest = true;
			return false;
		}
		
//	     !this.allowedMethods.contains(request.getMethod());
		return !allowedMethods.matcher(request.getMethod()).matches();
	}

}
