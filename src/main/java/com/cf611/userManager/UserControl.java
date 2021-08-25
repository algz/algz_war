package com.cf611.userManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.security.config.AUserDetailsService;
import com.cf611.definitionDetailManager.DefinitionDetail;
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
	
//	@Autowired
//    private LoginService loginService;
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	@RequestMapping("/currentuser")
	public CurrentUserVo getCurrentUser(){
		return service.getCurrentUser();
	}
	
	/**
	 * 获取用户列表
	 * @param pageParam
	 * @param au
	 * @return
	 */
	@RequestMapping("users")
	public ProTablePage<AUser> getUsers(ProTablePage<AUser> pageParam,AUser au){
		return service.getUsers(pageParam, au);
	}
	
	/**
	 * 获取用户
	 * @param userid
	 * @return
	 */
	@RequestMapping("user")
	public AUser getUser(String userid) {
		AUser u= service.getUser(userid);
		return u;
	}
	
	/**
	 * 保存用户
	 * @param u
	 * @return
	 */
	@PostMapping("saveuser")
	public String saveUser(AUser u) {
		return service.saveUser(u);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@PostMapping("deluser")
	public String delUser(String userid) {
		return service.delUser(userid);
	}
	
	/**
	 * 获取所有权限信息，转换成select控件值
	 * @return
	 */
	@RequestMapping("rolesforselectmap")
	public String getRolesForSelectMap(){
		return service.getRolesForSelectMap();
	}
	
	@RequestMapping(path = "/loginaccount",method = RequestMethod.POST)
	public UserVo loginAccount(UserVo vo) {
//		{"userName":"...","password":"....","autoLogin":true,"type":"account"}
//		UserVo vo=new UserVo();
//		vo.setStatus("ok");
//		vo.setCurrentAuthority(new String[]{"ADMIN"});
		return service.loginAccount(vo);
	}
	

	
}
