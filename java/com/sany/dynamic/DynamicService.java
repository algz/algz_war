package com.sany.dynamic;


public interface DynamicService {

	/**
	 * 1.获取动力学工况列表
	 * @param datajson
	 * @return
	 */
	public String GetDynamicGK(String datajson) ;
	
	
	/**
	 * 2.接收动力学仿真计算任务
	 * @param datajson
	 * @return
	 */
//	public String ReceiveDynamicSimulationTask(String datajson);
}
