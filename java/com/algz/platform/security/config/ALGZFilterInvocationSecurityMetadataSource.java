package com.algz.platform.security.config;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.algz.platform.security.authority.permissionManager.APermission;
import com.algz.platform.security.authority.permissionManager.APermissionRepository;
import com.algz.platform.security.authority.roleManager.ARole;

/**
 * 自定义权限拦截
 * 
 * Spring Security 的方法级别的权限控制的默认实现是把权限信息保存在内存中，
 * 而基于URL级别的权限控制的实际应用中通常是把权限信息保存在数据库中。 因此，URL级别的权限控制通常都需要我们自己实现从数据库中获取权限信息。
 * 自定义的权限数据获取类可以通过实现FilterInvocationSecurityMetadataSource 接口来实现。
 * 
 * @Description 自定义的资源（url）权限（角色）数据获取类
 * @author algz
 *
 */
@Component
public class ALGZFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private APermissionRepository apermissionService;

	/**
	 * 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合;
	 * 如果全都不匹配，则表示用户请求的资源（url)不需要权限（角色）即可访问.
	 * 
	 * (如果没有登录，并且设置.antMatchers("/common/**","/demo/**").permitAll()，
	 * 则("/common/**","/demo/**")资源不会运行到过滤器，就拦截返回到登录页面。)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filterInvocation=(FilterInvocation) object;
		// 打印请求地址和参数.
        String url = filterInvocation.getRequestUrl();
        System.out.println("访问的URL地址为(包括参数):" + url);
        url = filterInvocation.getRequest().getServletPath();
        System.out.println("访问的URL地址为:" + url);

        //方法1.采用自定义SQL查询
//        APermission per=apermissionService.findByPermissname(url);
//        List<ARole> roleList =Optional.ofNullable(per).orElse(new APermission()).getRoleList();
        
        //方法2.采用JPA接口查询
		// 查询具体某个接口的权限
		APermission ap = new APermission(url);
//		ExampleMatcher em=ExampleMatcher.matching().withMatcher("permissname", GenericPropertyMatchers.contains());
		Example<APermission> ex = Example.of(ap);
		List<ARole> roleList =apermissionService.findOne(ex).orElse(new APermission()).getRoleList();
		
		if (roleList == null || roleList.size() == 0) {
			// 请求路径没有配置权限，表明该请求接口可以任意访问.白名单访问方式.
//			return SecurityConfig.createList("ROLE_login");
			return null;
		}

		String[] attributes = new String[roleList.size()];
		for (int i = 0; i < roleList.size(); i++) {
			//返回请求对应的权限码
			attributes[i] = roleList.get(i).getAuthority();
		}
		return SecurityConfig.createList(attributes);
	}

	/**
	 * getAllConfigAttributes方法如果返回了所有定义的权限资源，Spring
	 * Security会在启动时校验每个ConfigAttribute是否配置正确，不需要校验直接返回null。
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * supports方法返回类对象是否支持校验，web项目一般使用FilterInvocation来判断，或者直接返回true。
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
