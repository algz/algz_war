package com.cf611.userManager;

import com.algz.platform.security.authority.userManager.AUser;
import com.cf611.util.ProTablePage;

public interface UserService {

	UserVo loginAccount(UserVo vo);
	
	CurrentUserVo getCurrentUser();

	ProTablePage<AUser> getUsers(ProTablePage<AUser> pageParam, AUser au);

	String delUser(String id);

	/**
	 * 保存用户
	 * @return
	 */
	String saveUser(AUser user);

	/**
	 * 获取所有权限信息
	 * @return
	 */
	String getRolesForSelectMap();

	AUser getUser(String userid);
}
