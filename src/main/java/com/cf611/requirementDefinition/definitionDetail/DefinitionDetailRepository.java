package com.cf611.requirementDefinition.definitionDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cf611.requirementDefinition.definition.Definition;

public interface DefinitionDetailRepository extends JpaRepository<DefinitionDetail,String>{

	@Query(nativeQuery = true, value = "SELECT * FROM CF_DEFINITIONDETAIL_VIEW ")
	List<DefinitionDetail> getDefinitionDetail();
}
