package com.cf611.regulationManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RegulationRepository extends JpaRepository<Regulation, String> {

	
	@Query(nativeQuery = true, value = "SELECT * FROM CF_REGULATION_VIEW where modelId=?1",
			countQuery="SELECT COUNT(1) FROM CF_REGULATION_VIEW where modelId=?1")
	public Page<Map<String,Object>> getRegulations(String modelId,Pageable pageable);

	
	/**
	 * DDL语句（insert,update,delete)。
	 * 必须添加 @ Modifying 注解，不然会报异常.(ORA-01002: 提取违反顺序)
	 * @param userid
	 * @param roleid
	 */
	@Modifying
	@Query(nativeQuery=true,value="insert into CF_REGULATION_INDICATOR (REGULATIONID,INDICATORID)values(:regulationId,:indicatorId)")
	void addRegulationIndicator(String regulationId,String indicatorId);
	
	/**
	 * DDL语句（insert,update,delete)。
	 * 必须添加 @ Modifying 注解，不然会报异常.(ORA-01002: 提取违反顺序)
	 * @param userid
	 * @param roleid
	 */
	@Modifying
	@Query(nativeQuery=true,value="delete from  CF_REGULATION_INDICATOR where REGULATIONID=:regulationId and INDICATORID=:indicatorId")
	void delRegulationIndicator(String regulationId,String indicatorId);
	
	@Query(nativeQuery = true, value = "select indicatorid from CF_REGULATION_INDICATOR where regulationid=?1")
	public List<String> getRegulationIndicator(String modelId,Pageable pageable);
	
	
	
	@Query(nativeQuery = true, value = "select * from CF_REGULATION_FULL_VIEW where definitionid=?1")
	public List<String> getRegulationFullViewByDefinitionId(String definitionid);
}
