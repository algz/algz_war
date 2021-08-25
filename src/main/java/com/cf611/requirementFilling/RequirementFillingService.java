package com.cf611.requirementFilling;

import java.util.List;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;

public interface RequirementFillingService {

	public ProTablePage<Definition> GetFillings(ProTablePage<Definition> pageParam,Definition definitionParam);
	
	public List<DefinitionDetail> getDefinitionDetail(DefinitionDetail definitionParam); 
	
	public String updateDefinitionDetail(DefinitionDetail params);
	
	public String submitDefinition(Definition params);
	
	public String feedbackDefinition(Definition params);
}
