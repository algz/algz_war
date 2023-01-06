package com.algz.platform.common.graph.edge;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EdgeRepository extends JpaRepository<Edge, String>{
	
//	/**
//	 * 标准SQL查询，不需要按命名规范定义。
//	 * @param name
//	 * @return
//	 */
//	@Query(name = "select * from ALGZ_PERMISSION where instr(?1,permissionname)>0",nativeQuery = true)
//	APermission findByPermissname(String name);
	
	@Query(name = "DELETE from ALGZ_GRAPH_EDGE WHERE GRAPHID=?1",nativeQuery = true)
	void deleteEdgeByGraphid(String graphid);
}
