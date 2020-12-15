package com.sany.other;

public interface OtherService {

	public String GetTaskState(String taskid, String kind);
	
	public String GetSimulationParts();
	
	public String GetSimulationReport(String taskid,String category);
	
	public String ReceiveWholeParamTemplate(String templateId);
}
