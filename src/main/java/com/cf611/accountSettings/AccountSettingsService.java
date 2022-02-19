package com.cf611.accountSettings;

import org.springframework.web.bind.annotation.RequestBody;

import com.algz.userExt.UserExt;

public interface AccountSettingsService {

	public String AccountSettingCurrentUser();
	
	public String SaveCurrentUserPassword(String password);
	
	public String SaveCurrentUserExt(UserExt userExt);
	
}
