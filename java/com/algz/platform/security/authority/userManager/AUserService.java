package com.algz.platform.security.authority.userManager;

public interface AUserService {

	public void SaveUserPassword(String userid,String password);
	
	public AUser findById(String userid);
}
