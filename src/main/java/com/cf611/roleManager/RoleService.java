package com.cf611.roleManager;

import com.algz.platform.security.authority.roleManager.ARole;
import com.cf611.util.ProTablePage;

public interface RoleService {

	/**
	 * 获取角色列表
	 * @param pageParams
	 * @param roleParams
	 * @return
	 */
	ProTablePage<ARole> getRoles(ProTablePage<ARole> pageParam, ARole roleParam);

}
