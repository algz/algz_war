package com.algz.platform.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.algz.platform.security.authority.userManager.AUser;

public class SpringSecurityUtils {

	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static AUser getCurrentUser() {
		AUser user = (AUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    //String name = user.getUsername(); //get logged in username
	    return user;
    }
	
	/**
	 * 获取当前鉴权对象
	 * @return
	 */
	public static Authentication getCurrentAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in userna
	    System.out.print(name);
	    return auth;
//        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
//        return ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
    }




}
