package com.cf611.systemConfig.roleConfig;

import com.algz.platform.security.authority.roleManager.ARole;
import com.cf611.util.ProTablePage;

public interface RoleConfigService {

	/**
	 * 获取角色列表
	 * @param pageParams
	 * @param roleParams
	 * @return
	 */
	ProTablePage<ARole> getRoles(ProTablePage<ARole> pageParam, ARole roleParam);

}
