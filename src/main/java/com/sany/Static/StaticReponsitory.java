package com.sany.Static;

import java.util.List;

public interface StaticReponsitory {

	public List<?> GetCondinate(String partid);
	
	public List<?> ReceiveStaticGK(String partid);
	
	public String SaveStaticTask(StaticTask task);
	
	public String GetSimulationReport(String taskid, String category);
}
