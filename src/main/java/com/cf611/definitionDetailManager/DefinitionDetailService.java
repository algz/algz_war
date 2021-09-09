package com.cf611.definitionDetailManager;

import java.util.List;

import com.cf611.util.ProTablePage;

public interface DefinitionDetailService {

	public String AddDefinitionDetail(DefinitionDetail detail);
	
	public ProTablePage<DefinitionDetail> GetDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetailParam);
	
	public List<DefinitionDetail> getDefinitionDetailByDefinitionId(String definitonId);
	
	public String updateDefinitionDetail(DefinitionDetail param);
}
