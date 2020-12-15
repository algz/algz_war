package com.sany.other;

import java.util.List;

public interface OtherReponsitory {

	public List<?> GetTaskState(String taskid,String kind);
	
	public List<?> GetSimulationParts();
	
	public List<?> GetWholeParamTemplate(String templateId);
	
	public List<?> GetWholeParamTemplateParam(String templateId);
	
	public String GetSimulationReport(String taskid,String category);
}
