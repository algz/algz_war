/**
 * 
 */
package com.algz.platform.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;

/**
 * @author algz
 *
 */
public class AUserDetailsService implements UserDetailsService{

	 private final AUserRepository dao;
	 
	 public  AUserDetailsService(AUserRepository dao) {
		this.dao=dao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AUser user=dao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not authorized.");
		}
//	    GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
//	    UserDetails userDetails = (UserDetails)new User(dBuserName,
//	            activeUserInfo.getPassword(), Arrays.asList(authority));
		return user;
	}

}
