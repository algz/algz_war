package com.cf611.permissionManager;

import com.algz.platform.security.authority.permissionManager.APermission;
import com.cf611.util.ProTablePage;

public interface PermissionService {

	/**
	 * 获取权限列表
	 * @param pageParam
	 * @param permissionParams
	 * @return
	 */
	ProTablePage<APermission> getPermissons(ProTablePage<APermission> pageParam, APermission permissionParams);

}
