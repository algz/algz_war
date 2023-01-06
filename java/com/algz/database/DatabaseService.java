package com.algz.database;

import java.util.List;
import java.util.Map;

public interface DatabaseService {

	/**
	 * 查询所有用户（模式）
	 */
	public List<Map<String,Object>> findAllUsers();
	
	/**
	 * 查询所有表空间
	 * @return
	 */
	public List<Map<String,Object>> findAllTableSpaces();
	
	/**
	 * 查询所有表
	 * @return
	 */
	public List<Map<String,Object>> findAllTables(String owner);
	
	/**
	 * 查询所有视图
	 * @return
	 */
	public List<Map<String,Object>> findAllViews(String owner);
	
	/**
	 * 执行SQL
	 * @param sql
	 * @return
	 */
	public int ExecSQL(String sql);
	
	void a();
}
