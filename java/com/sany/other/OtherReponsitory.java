package com.sany.other;

import java.util.List;

import com.algz.platform.common.file.pathencode.APathCode;

public interface OtherReponsitory {

	public List<?> GetTaskState(String taskid,String kind);
	
	public List<?> GetSimulationParts();
	
	public List<?> GetWholeParamTemplate(String templateId);
	
	public List<?> GetWholeParamTemplateParam(String templateId);
	
	public APathCode GetSimulationReport(String taskid,String category);
}
