package com.cf611.definitionDetailManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DefinitionDetailRepository extends JpaRepository<DefinitionDetail,String>{

	@Query(nativeQuery = true, value = "SELECT * FROM CF_DEFINITIONDETAIL_VIEW where definitionId=?1")
	public List<Map<String,Object>> getDefinitionDetail(String definitionId);
	
//	@Query(nativeQuery = true, 
//			value = "select definitiondetailid id,createdate,creator,definitionid,indicatorid,indicatorName,definitiondetailstate state,indicatorVal val,version "
//					+ "from cf_definitiondetail_view ")
//	List<DefinitionDetail> getDefinitionDetail();
	
//	@Query(nativeQuery = false, 
//			value = "select  new com.cf611.definitionDetailManager.DefinitionDetail(userName,password,firstName,lastName,email) definitiondetailid id,createdate,creator,definitionid,indicatorid,indicatorName,definitiondetailstate state,indicatorVal val,version "
//					+ "from cf_definitiondetail_view ")
//	List<DefinitionDetail> getDefinitionDetail1();
	

	
	
	
}
