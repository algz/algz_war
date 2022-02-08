package com.cf611.requirmentDataBase.modelBase;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelRepository extends JpaRepository<Model, String> {

	/**
	 * 自定义查询
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM cf_model_view where id=?1")
	public Map<String,Object> getModelForMapList(String id);
}
