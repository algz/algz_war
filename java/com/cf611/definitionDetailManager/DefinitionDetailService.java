package com.cf611.definitionDetailManager;

import java.util.List;

import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailView;
import com.cf611.util.ProTablePage;

public interface DefinitionDetailService {

	public String AddDefinitionDetail(DefinitionDetail detail);
	
	public ProTablePage<DefinitionDetail> GetDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetailParam);
	
	public List<DefinitionDetailView> getDefinitionDetailByDefinitionId(String definitonId);
	
	public String updateDefinitionDetail(DefinitionDetail param);
}
