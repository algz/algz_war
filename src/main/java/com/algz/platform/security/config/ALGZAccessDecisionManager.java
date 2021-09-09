package com.algz.platform.security.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @Description 自定义的权限控制管理器(访问决策管理器),主要是比对用户所拥有的权限和用户请求的URL或方法所需要的权限，
 * 以决定是否允许用户执行请求，如果比对成功，则允许，否则拒绝。
 * @author algz
 *
 */
@Component
public class ALGZAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 当需要多个权限时只要有一个符合则校验通过，即或的关系，想要并的关系只需要修改这里的逻辑即可。
	 * @param authentication   包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
	 * @param object   就是FilterInvocation对象，可以得到request等web资源。
	 * @param configAttributes 本次访问需要的权限集.
	 * 由 ALGZFilterInvocationSecurityMetadataSource.getAttributes(object) 方法提供.
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		/**
		 * 如果请求的资源不需要权限，则直接放行
		 */
		if (configAttributes == null || configAttributes.size() <= 0) {
			return;
		}
		/**
		 * 判断用户所拥有的权限是否是资源所需要的权限之一，如果是则放行，否则拦截
		 */
		Iterator<ConfigAttribute> iter = configAttributes.iterator(); //来源 SecurityMetadataSource
		while (iter.hasNext()) {
            //获取当前请求需要的权限码
			String needRole = iter.next().getAttribute();
           //当前用户具有的"权限码"与访问的"权限码"进行匹配
			for (GrantedAuthority grantRole : authentication.getAuthorities()) {
				if (needRole.trim().equals(grantRole.getAuthority().trim())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
