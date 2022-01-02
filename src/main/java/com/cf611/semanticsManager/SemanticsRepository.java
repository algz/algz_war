package com.cf611.semanticsManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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
public interface SemanticsRepository extends JpaRepository<Semantics,String>,JpaSpecificationExecutor<Semantics>{

//	public Page<Semantics> findAllByOrderByCreateDateAtDesc(Example<Semantics> example, Pageable pageable);
}
