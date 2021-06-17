package com.cf611.userManager;

public interface UserService {

	UserVo loginAccount(UserVo vo);
	
	CurrentUserVo getCurrentUser(CurrentUserVo vo);
}
