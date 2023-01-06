package com.algz.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo/sql")
@RestController
public class SQLDemoControl {

	
	/**
	 * 支持Get,Post
	 * @param sql
	 * @return
	 */
	@RequestMapping("executedatatable")
	public List<?> ExecuteDataTable(String sql) {
		try {
			return null; 
		}catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("executedatatable1")
	public Page<?> ExecuteDataTable(String sql,Pageable pageable) {
		try {
			Page <?> page=null;//service.ExecuteDataTable(sql,pageable);
			return page; 
		}catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("executescalar")
	public BigDecimal ExecuteScalar(String sql) {
		try {
			return null;//service.ExecuteScalar(sql);
		}catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("executenonquery")
	public Integer ExecuteNonQuery(String sql) {
		try {
			return null;//service.ExecuteNonQuery(sql);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 要传数组,必须设置 traditional: true,否则获取不到数组数据.
	 *  $.ajax({
            type:'post',
            url: "http://localhost:8080/algz/common/sql/executebatchnonquery",
             data:{sql : [1,2]},
             traditional: true,//必须指定为true,适用于servlet,  sql=1&sql=2; false,适用于PHP和Ruby on Rails,用于深度序列化 , sql[]=1& sql[]=1
           ...
	 * @param sql
	 * @return
	 */
	@RequestMapping("executebatchnonquery")
	public Integer ExecuteBatchNonQuery(String[] sql) {
		try {		
			return 1;//service.ExecuteNonQuery(null);
		}catch (Exception e) {
			return null;
		}
	}
	
@RequestMapping("executebatchnonquery1")
@ResponseBody
	public void ExecuteBatchNonQuery1(String sql, HttpServletRequest request) {
		try {		
			System.out.println(".......");
//			service.ExecuteNonQuery(null);
		}catch (Exception e) {
			//return null;
		}
	}
}
