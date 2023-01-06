package com.algz.flow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cf611.requirementDefinition.definition.Definition;

/**
 * 
 * @author algz
 *
 */
public interface FlowViewRepository extends JpaRepository<FlowView,String>,JpaSpecificationExecutor<FlowView>{

}
