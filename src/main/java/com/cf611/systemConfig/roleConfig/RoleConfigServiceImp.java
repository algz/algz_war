package com.cf611.systemConfig.roleConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.algz.platform.security.authority.roleManager.ARole;
import com.algz.platform.security.authority.roleManager.ARoleRepository;
import com.cf611.util.ProTablePage;

@Service
public class RoleConfigServiceImp implements RoleConfigService {

	@Autowired
	private ARoleRepository repository;
	
	/**
	 * 获取角色列表
	 * @param pageParams
	 * @param roleParams
	 * @return
	 */
	@Override
	public ProTablePage<ARole> getRoles(ProTablePage<ARole> pageParam,ARole roleParam){
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		Page<ARole> page=repository.findAll(Example.of(roleParam), pageable);// 没有数据时，返回空列表;
		pageParam.setPage(page);
		return pageParam;
	}
}
