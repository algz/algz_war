package com.algz.database.oracle;

import java.util.List;
import java.util.Map;

public interface OracleCustomRepository {
	/**
	 * 执行SQL
	 * @param sql
	 * @return
	 */
	public int ExecSQL(String sql);
	
	/**
	 * 查询SQL
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> QuerySQL(String sql); 
}
