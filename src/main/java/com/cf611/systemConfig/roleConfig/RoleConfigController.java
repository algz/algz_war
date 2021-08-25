package com.cf611.systemConfig.roleConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.security.authority.roleManager.ARole;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/systemconfig/roleconfig")
public class RoleConfigController {

	@Autowired
	private RoleConfigService service;
	
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
