package com.algz.platform.common.sql.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.algz.demo.Goods;
import com.algz.platform.common.sql.dao.SQLRepository;



@RequestMapping("/common/sql")
@RestController
public class SQLControl {

	private static Logger LOGGER = LoggerFactory.getLogger(SQLControl.class);
	//private final Logger log= LoggerFactory.getLogger(SQLControl.class);
	
	@Autowired
	private SQLRepository service;
	
	/**
	 * 支持Get,Post
	 * @param sql
	 * @return
	 */
	@RequestMapping("executedatatable")
	public List<?> ExecuteDataTable(String sql) {
		try {
//			if(sql.contains("select")) {
//				return service.ExecuteDataTable(sql);
//			}else if(sql.contains("insert")) {
//				service.ExecuteNonQuery(sql);
//				return null; 
//			}else if(sql.contains("count")) {
//				return null; 
//			}
			 return service.ExecuteDataTable(sql);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 分页查询
	 * @param sql
	 * @param pageable
	 * @return
	 */
	@RequestMapping("executedatatable/page")
	public Page<?> ExecuteDataTable(String sql,Pageable pageable) {
		try {
			Page <?> page=service.ExecuteDataTable(sql,pageable);
			return page; 
		}catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("executescalar")
	public BigDecimal ExecuteScalar(String sql) {
		try {
			return service.ExecuteScalar(sql);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 提交不返回的SQL命令
	 * @param sql
	 * @return
	 */
	@RequestMapping("executenonquery")
	public Integer ExecuteNonQuery(String sql) {
		try {
			LOGGER.info("--------ExecuteNonQuery-----------");
			 service.ExecuteNonQuery(sql);
			 return 1;
		}catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 要传数组,必须设置 traditional: true,否则获取不到数组数据.
	 *  $.ajax({
            type:'post',
            url: "http://localhost:8080/algz/common/sql/executebatchnonquery",
             data:{sql : [1,2]},   //Form Data : sql=1&sql=2
             traditional: true,//必须指定为true,适用于servlet,  sql=1&sql=2; false,适用于PHP和Ruby on Rails,用于深度序列化 , sql[]=1& sql[]=1
           ...
	 * @param sql
	 * @return
	 */
	@RequestMapping("executenonquery/batch")
	public Integer ExecuteBatchNonQuery(String[] sql) {
		try {		
			LOGGER.info("==========ExecuteBatchNonQuery==========");
			for(String s : sql) {
				service.ExecuteNonQuery(s);
			}
			return 1;
		}catch (Exception e) {
			return null;
		}
	}
}
