package com.sany.Static.other;

import java.util.List;

import com.sany.Static.StaticTask;

public interface StaticReponsitory1 {
	
	List<?> GetDynamicZH(String modelId);
	
	public String SaveStaticTask(StaticTask task);
}
