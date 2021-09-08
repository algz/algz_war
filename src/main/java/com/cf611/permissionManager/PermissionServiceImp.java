package com.cf611.permissionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.security.authority.permissionManager.APermission;
import com.algz.platform.security.authority.permissionManager.APermissionRepository;
import com.cf611.util.ProTablePage;

@Transactional(readOnly = true)
@Service
public class PermissionServiceImp implements PermissionService {

	@Autowired
	private APermissionRepository repository;
	
	/**
	 * 获取权限列表
	 * @param pageParam
	 * @param permissionParams
	 * @return
	 */
	@Override
	public ProTablePage<APermission> getPermissons(ProTablePage<APermission> pageParam,APermission permissionParams){
		// 参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size
		// 设置每页数据的条数;参数3，排序,字符串参数("createDate")是对象(definition)属性名称,大小写。
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize(),
				Sort.by("createDate").descending());
		Page<APermission> page=repository.findAll(Example.of(permissionParams), pageable);// 没有数据时，返回空列表;
		pageParam.setPage(page);
		return pageParam;
	}

	
}
