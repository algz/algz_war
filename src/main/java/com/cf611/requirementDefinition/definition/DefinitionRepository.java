package com.cf611.requirementDefinition.definition;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;

/**
 * 
 * Java 接口可以多继承
 * JpaRepository 中有常用的 CRUD 、分页、排序等方法
 * JpaSpecificationExecutor 可以实现任意的复杂查询
 * 
 * @author algz
 *
 */
public interface DefinitionRepository extends JpaRepository<Definition,String>,JpaSpecificationExecutor<Definition>{

	/**
	 * 重新实现此方法
	 */
	@Query(nativeQuery = true,value="select * from cf_definitions_view")
	public Page<DefinitionView> findViewAll(@Nullable Specification<DefinitionView> spec, Pageable pageable);
	
	/**
	 * 获取最大版本号（当前版本号）
	 * 向上查询和向下查询
	 */
	@Query(nativeQuery = true,value="select Max(to_number(version)+1) version from\r\n"
			+ "(select Max(to_number(version))version from cf_definitions def "
			+ "start with def.id=?1  connect by prior def.parentid=def.id\r\n"
			+ "union  \r\n"
			+ "select Max(to_number(version))version from cf_definitions def  start with def.id=?1 "
			+ " connect by def.parentid=prior def.id)")
	public Integer getMaxVersion(String id);
}
