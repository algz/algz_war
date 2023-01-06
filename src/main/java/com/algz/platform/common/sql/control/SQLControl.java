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

import com.algz.platform.common.crypto.RSAEncryption;
import com.algz.platform.common.sql.dao.SQLRepository;


/**
 * 需要使用公钥加密传输数据，才能执行sql传输数据。
 * @author algz
 *
 */
@RequestMapping("/common/sql")
@RestController
public class SQLControl {

	private static final Logger LOGGER = LoggerFactory.getLogger(SQLControl.class);
	//private final Logger log= LoggerFactory.getLogger(SQLControl.class);
	
	@Autowired
	private SQLRepository service;
	
	/**
	 * 支持Get,Post
	 * eg.: get  http://localhost:8080/algz/common/sql/executedatatable?sql=
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
			sql=DecryptoSql(sql);
			if(sql!=null) {
				return service.ExecuteDataTable(sql);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
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
	
	/**
	 * 解密SQL参数，成功返回正确的SQL语句，失败返回null.
	 * @param sql
	 * @return
	 */
	private String DecryptoSql(String sqlParam) {
		//String sqlParam="Lr3S96hFRbL/ArhwXLb0M8s2rPziv90V32pq4iUd97417tuQDuBBi0nGgEmjb464AFLGndygolCxfSEXR3I8qqUj4UULVTR0KiQXL/8vp/aRMxm7sE7iyr32bJnSl3Gs2hEXHM+yTTm1z7rmU6JuMzal24Bf5pI8XXFgiUGNDus=";
		//公钥
		String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1icuM7QxgJlCDEo8siRqBOe2fcnCvquN6o/Gd/MtEUf8AvPRMvKKxtBTyB9pUyP8N9/6qNR3UMombSDIDJAHOP/YUPSOCxASwQyVeoDLzPy8XFeHLCZCm3LVfZqyLRaQG05pSIU2f65XcoANvkPiGclvrHsufHtcrU2FXkbGDPQIDAQAB";
		//私钥
		String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALWJy4ztDGAmUIMSjyyJGoE57Z9ycK+q43qj8Z38y0RR/wC89Ey8orG0FPIH2lTI/w33/qo1HdQyiZtIMgMkAc4/9hQ9I4LEBLBDJV6gMvM/LxcV4csJkKbctV9mrItFpAbTmlIhTZ/rldygA2+Q+IZyW+sey58e1ytTYVeRsYM9AgMBAAECgYB1tXk0UWfa2D0QH/KRz/xNmkAHSO7kGIwTM0YxSH6MOBZ+ZgaJ0y4yxy9ll83rd8BZUFniBzrmIjazZuZ2vtwjxgtbdzJcOUuXVx9UQ3Q9VccFoenoXA2ev39/BdLuDjFTabDjsvIFAnr6vfAFdeVZAfrAO0Hjq6ngkQAxtzmOQQJBAN3ajRpcdes8v/cbf/+dEC5ulX/lRuYs8REc1e9IOYI9NqyGVXm4Xl/JIQTJ7xEz6LUqU120joKl/MuEV8cMuWUCQQDRer148HQFvG2iz0e8Zy5k6RDdNZn+s34At6TMC/dwY67LqY1G4aImaNRG1Jv4hA2wKI4RJ0PO2QsvuNYnkXD5AkEAkODecK6c2wgLHKY6yoSZQpk4zBLAYCRNMS2xj9eMpr6u66+2NgzupILGK0hr9MH/X//J/VGiFzOqiIVehUCL+QJAdO3UcXuW59TERgr5+fqGdSF7nl7bNMcwYGoh/Rv45K1f1qMwDw7HmjUNuPPYlCHyGhCpLH4EZaPVkK/WKoQO0QJAG8SYMoX/aOCZr5HaSV5++d5u96HALrxSSd6epay5pzXm++FIQ4XqWJx+mmg3KfplU1C8R0J0XtpwAL+sotcaJQ==";
		String dc=RSAEncryption.decrypt(sqlParam, privateKey);
		return dc;
	}
}
