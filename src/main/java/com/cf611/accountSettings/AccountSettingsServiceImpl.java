package com.cf611.accountSettings;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.security.authority.userManager.AUserService;
import com.algz.platform.utility.JsonUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.algz.userExt.UserExt;
import com.algz.userExt.UserExtService;
import com.ctc.wstx.util.StringUtil;

import antlr.StringUtils;

@Service
public class AccountSettingsServiceImpl implements AccountSettingsService {

	@Autowired
	private AUserService aUserService;
	
	@Autowired
	private UserExtService userExtService; 
	
	@Override
	public String AccountSettingCurrentUser() {
		AUser user=SpringSecurityUtils.getCurrentUser();
		user=aUserService.findById(user.getUserid());
		UserExt userExt=userExtService.getUserExt(user.getUserid());
		userExt.setName(user.getName());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("data", userExt);
		return JsonUtils.objectToJson(map);
	}

	@Override
	public String SaveCurrentUserPassword(String password) {
		AUser user=SpringSecurityUtils.getCurrentUser();
		aUserService.SaveUserPassword(user.getUserid(), password);
		return null;
	}

	@Transactional
	@Override
	public String SaveCurrentUserExt(UserExt userExt) {
		AUser user=SpringSecurityUtils.getCurrentUser();
		user=aUserService.findById(user.getUserid());
		if(userExt.getName()!=null&&!userExt.getName().equals("")) {
			user.setName(userExt.getName());
		}
		UserExt ue=userExtService.getUserExt(user.getUserid());
		if(userExt.getEmail()!=null) {
			ue.setEmail(userExt.getEmail());
		}
		
		return null;
	}

}
