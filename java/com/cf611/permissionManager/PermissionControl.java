package com.cf611.permissionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.security.authority.permissionManager.APermission;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/permissionmanager")
public class PermissionControl {

	@Autowired
	private PermissionService service;
	
	@RequestMapping("permissions")
	public ProTablePage<APermission> getPermissons(ProTablePage<APermission> pageParam,APermission permissionParams){
		return service.getPermissons(pageParam, permissionParams);
	}
}
