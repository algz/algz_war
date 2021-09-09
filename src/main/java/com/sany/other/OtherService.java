package com.sany.other;

import com.algz.platform.common.file.pathencode.APathCode;

public interface OtherService {

	public String GetTaskState(String taskid, String kind);
	
	public String GetSimulationParts();
	
	public APathCode GetSimulationReport(String taskid,String category);
	
	public String ReceiveWholeParamTemplate(String templateId);
}
