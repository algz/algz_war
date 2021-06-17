package com.cf611.userManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.security.authority.userManager.AUserRepository;
import com.cf611.util.ProTablePage;

/**
 * @author algz
 *
 */
@RequestMapping("/user")
@RestController
public class UserControl {

	@Autowired
	private UserService service;
	

	
	@RequestMapping(path = "/loginaccount",method = RequestMethod.POST)
	public UserVo loginAccount(UserVo vo) {
//		{"userName":"...","password":"....","autoLogin":true,"type":"account"}
//		UserVo vo=new UserVo();
//		vo.setStatus("ok");
//		vo.setCurrentAuthority(new String[]{"ADMIN"});
		return service.loginAccount(vo);
	}
	
	@RequestMapping("/currentuser")
	public CurrentUserVo getCurrentUser(CurrentUserVo cuv){
		return service.getCurrentUser(cuv);
	}
}
