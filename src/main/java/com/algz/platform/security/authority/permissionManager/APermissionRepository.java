package com.algz.platform.security.authority.permissionManager;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface APermissionRepository extends JpaRepository<APermission, String>{
	
	@Query(name = "select * from ALGZ_PERMISSION where instr(?1,permissionname)>0",nativeQuery = true)
	APermission findByPermissname(String name);
}
