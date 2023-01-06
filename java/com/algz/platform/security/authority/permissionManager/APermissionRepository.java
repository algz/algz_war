package com.algz.platform.security.authority.permissionManager;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface APermissionRepository extends JpaRepository<APermission, String>{
	
	/**
	 * 标准SQL查询，不需要按命名规范定义。
	 * @param name
	 * @return
	 */
	@Query(name = "select * from ALGZ_PERMISSION where instr(?1,permissionname)>0",nativeQuery = true)
	APermission findByPermissname(String name);
}
