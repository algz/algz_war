package com.algz.userExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExtServiceImpl implements UserExtService{

	@Autowired
	private UserExtRepository repository;
	
	@Override
	public UserExt getUserExt(String userid) {
		return repository.findById(userid).orElse(new UserExt());
	}

}
