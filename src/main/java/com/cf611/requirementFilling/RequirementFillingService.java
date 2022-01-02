package com.cf611.requirementFilling;

import java.util.List;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.indicatorManager.Indicator;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

public interface RequirementFillingService {

	public ProTablePage<DefinitionView> GetFillings(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam);
	
	public List<DefinitionDetail> getDefinitionDetail(DefinitionDetail definitionParam); 
	
	public String updateDefinitionDetail(DefinitionDetail params);
	
	public String submitDefinition(Definition params);
	
	public String feedbackDefinition(Definition params,String description);
	
	List<Indicator> getIndicatorsForSelect(Indicator indicatorParam);
}
