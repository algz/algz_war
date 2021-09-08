package com.cf611.roleManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.security.authority.roleManager.ARole;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/rolemanager")
public class RoleControl {

	@Autowired
	private RoleService service;
	
	/**
	 * 获取角色列表
	 * @param pageParam
	 * @param roleParam
	 * @return
	 */
	@RequestMapping("roles")
	public ProTablePage<ARole> getRoles(ProTablePage<ARole> pageParam,ARole roleParam){
		return service.getRoles(pageParam, roleParam);
	}
}
