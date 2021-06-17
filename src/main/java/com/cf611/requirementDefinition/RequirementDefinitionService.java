package com.cf611.requirementDefinition;

import java.util.List;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionDetail.DefinitionDetail;
import com.cf611.util.ProTablePage;

public interface RequirementDefinitionService {

	public ProTablePage<Definition> GetDefinitions(ProTablePage<Definition> pageParam,Definition definitionParam);
	
	public ProTablePage<DefinitionDetail> GetDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetail);
}
