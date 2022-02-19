package com.cf611.accountSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.userExt.UserExt;

@RestController
@RequestMapping("/accountsettings")
public class AccountSettingsController {

	@Autowired
	private AccountSettingsService service;
	
	@RequestMapping("accountSettingCurrentUser")
	public String AccountSettingCurrentUser() {
		return service.AccountSettingCurrentUser();
	}


	@PostMapping("saveCurrentUserPassword")
	public String SaveCurrentUserPassword(@RequestBody UserExt userExt) {
		return service.SaveCurrentUserPassword(userExt.getPassword());
	}
	
	@PostMapping("saveCurrentUserExt")
	public String SaveCurrentUserExt(@RequestBody UserExt userExt) {
		return service.SaveCurrentUserExt(userExt);
	}
}
