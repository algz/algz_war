package com.algz.platform.common.sql.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SQLRepository {
	public List<?> ExecuteDataTable(String sql);
	
//	public List<?> ExecuteDataTable(String sql,Map param);
	
	public Page<?> ExecuteDataTable(String sql,Pageable pageable);
	
	
	
	public BigDecimal  ExecuteScalar(String sql);
	
	public Integer ExecuteNonQuery(String sql) ;
}
