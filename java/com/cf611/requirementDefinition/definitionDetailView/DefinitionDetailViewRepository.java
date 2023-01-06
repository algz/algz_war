package com.cf611.requirementDefinition.definitionDetailView;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cf611.definitionDetailManager.DefinitionDetail;

public interface DefinitionDetailViewRepository extends JpaRepository<DefinitionDetailView,String>{

	List<DefinitionDetailView> findDefinitionDetailViewByDefinitionId(String definitionId);
}
