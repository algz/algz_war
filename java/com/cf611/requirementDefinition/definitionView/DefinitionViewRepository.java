package com.cf611.requirementDefinition.definitionView;

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

/**
 * 
 * Java 接口可以多继承
 * JpaRepository 中有常用的 CRUD 、分页、排序等方法
 * JpaSpecificationExecutor 可以实现任意的复杂查询
 * 
 * @author algz
 *
 */
public interface DefinitionViewRepository extends JpaRepository<DefinitionView,String>,JpaSpecificationExecutor<DefinitionView>{

}
