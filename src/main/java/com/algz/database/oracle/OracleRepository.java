package com.algz.database.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algz.database.DatabaseRepository;
import com.algz.demo.typicalExample.TypicalExample;

public interface OracleRepository extends JpaRepository<TypicalExample,String>,OracleCustomRepository,DatabaseRepository{

	/**
	 * 查询所有用户（模式）
	 */
	@Query(nativeQuery = true,value = "SELECT * FROM dba_users")
	public List<Map<String,Object>> findAllUsers();
	
	/**
	 * 查询所有表空间
	 * @return
	 */
	@Query(nativeQuery = true,value = "select * from dba_data_files")
	public List<Map<String,Object>> findAllTableSpaces();
	
	/**
	 * 查询所有表
	 * @return
	 */
	@Query(nativeQuery = true,value = "select * from all_tables where owner=?1")
	public List<Map<String,Object>> findAllTables(String owner);
	
	/**
	 * 查询所有视图
	 * @return
	 */
	@Query(nativeQuery = true,value = "select * from all_views where owner=?1")
	public List<Map<String,Object>> findAllViews(String owner);
	

}
