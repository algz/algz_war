package com.algz.platform.common.graph.node;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NodeRepository extends JpaRepository<Node, String>{
	
//	/**
//	 * 标准SQL查询，不需要按命名规范定义。
//	 * @param name
//	 * @return
//	 */
//	@Query(name = "select * from ALGZ_PERMISSION where instr(?1,permissionname)>0",nativeQuery = true)
//	APermission findByPermissname(String name);
	
	@Query(name = "DELETE from ALGZ_GRAPH_NODE WHERE GRAPHID=?1",nativeQuery = true)
	void deleteNodeByGraphid(String graphid);
}
