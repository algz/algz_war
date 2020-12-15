package com.algz.platform.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 *  权限拦截器
 * 1.自定义一个既继承了AbstractSecurityInterceptor类又实现了Filter接口的过滤器，并重写和实现其中的方法。
 * 2.把自定义的权限数据获取类的对象注入到自定义的过滤器中,并且要能够通过调用重写的public SecurityMetadataSource obtainSecurityMetadataSource()方法即可获取到自定义的权限数据获取类的对象。
 * 3.以构造方法的形式把自定义的权限控制管理器注入到自定义的过滤器中。
 * @author algz
 *
 */
@Service
public class ALGZAbstractSecurityInterceptor extends AbstractSecurityInterceptor  implements Filter{

    /**
     * 2.1注入自定义的资源（url）权限（角色）的数据源.
     */
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
	/**
	 * 2.2 获取到自定义的权限数据获取类的对象
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
    
    /**
     *3. 注入自定义的权限验证管理器
     */
    @Autowired
    public void setMyAccessDecisionManager(ALGZAccessDecisionManager accessDecisionManager) {
         super.setAccessDecisionManager(accessDecisionManager);
    }



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 FilterInvocation fi = new FilterInvocation(request, response, chain);
	        //fi里面有一个被拦截的url
	        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
	        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
	        InterceptorStatusToken token = super.beforeInvocation(fi);
	        try {
	        //执行下一个拦截器
	            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
	        } finally {
	            super.afterInvocation(token, null);
	        }
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}
	
}
