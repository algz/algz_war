package com.algz.platform.common.graph;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GraphRepository extends JpaRepository<Graph, String>{
	
//	/**
//	 * 标准SQL查询，不需要按命名规范定义。
//	 * @param name
//	 * @return
//	 */
//	@Query(name = "select * from ALGZ_PERMISSION where instr(?1,permissionname)>0",nativeQuery = true)
//	APermission findByPermissname(String name);
}
