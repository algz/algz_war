package com.algz.platform.security.authority.userManager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AUserServiceImpl implements AUserService{

	@Autowired
	private AUserRepository repository;
	
	@Transactional
	@Override
	public void SaveUserPassword(String userid, String password) {
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		String str=bc.encode(password);
		AUser u=repository.findById(userid).get();
		u.setPassword(str);
	}

	@Override
	public AUser findById(String userid) {
		return repository.findById(userid).get();
	}

}
