package com.algz.flow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @author algz
 *
 */
public interface FlowRepository extends JpaRepository<Flow,String>,JpaSpecificationExecutor<Flow>{

}
