package com.cf611.requirmentJudge;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

public interface RequirementJudgeService {
	public ProTablePage<DefinitionView> GetJudges(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam);
	
	String receiveDefinition(Definition params);
}
