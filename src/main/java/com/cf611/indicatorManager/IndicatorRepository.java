package com.cf611.indicatorManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IndicatorRepository extends JpaRepository<Indicator,String> {

	@Query(nativeQuery = true, value = "SELECT * FROM CF_INDICATOR_SEMANTICS where IndicatorId=?1")
	public List<Map<String,Object>> getIndicatorSemanticsForMapList(String IndicatorId);
	
	@Query(nativeQuery = true, value = "SELECT count(1) FROM CF_INDICATOR_SEMANTICS where INDICATORID=?1 and SEMANTICSID=?2")
	public Long countIndicatorSemantics(String IndicatorId,String semanticsId);
	
	/**
	 * 新增指标与语义关联
	 * @param id
	 * @param indicatorId
	 * @param semanticsId
	 * @return
	 */
//	@Transactional
	@Modifying//(clearAutomatically = true)   //jpa insert,update 必须添加 @Modifying 。
	@Query(nativeQuery=true,value="insert into CF_INDICATOR_SEMANTICS (ID,INDICATORID,SEMANTICSID)values(?1,?2,?3)")
	public int addIndicatorSemantics(String id,String indicatorId,String semanticsId);
	
	/**
	 * 删除指标与语义关联
	 * @param indicatorId
	 * @param semanticsId
	 * @return
	 */
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from CF_INDICATOR_SEMANTICS where INDICATORID=?1 and SEMANTICSID=?2")
	public int delIndicatorSemantics(String indicatorId,String semanticsId);
}
