/**
 * 
 */
package com.sany.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author algz
 *
 */
@WebService(
		//name = "CommonServiceA", // 暴露服务名称
		//targetNamespace = "http://service.webservice.kexin.com"// 命名空间,一般是接口的包名倒序
		)
public interface DesignPlatformService {

	/**
	 * 1.获取动力学工况列表
	 * @param datajson
	 * @return
	 */
	public String ReceiveDynamicGK(@WebParam String datajson);
	
	/**
	 * 2.接收动力学仿真计算任务
	 * @param datajson
	 * @return
	 */
	public String ReceiveDynamicSimulationTask(@WebParam String datajson);
	
	/**
	 * 1.获取静力学工况列表
	 * @param partid
	 * @return
	 */
	public String ReceiveStaticGK(String partid);
	
	/**
	 * 2.获取坐标系列表
	 * @return
	 */
	public String GetCondinate(String partid);
	
	/**
	 * 3.接收静力学仿真计算任务
	 * @param datajson
	 * @return
	 */
	public String ReceiveStaticSimulationTask(String datajson);
	
	/**
	 * 1.获取整机参数模板
	 * @param templateId
	 * @return
	 */
	public String ReceiveWholeParamTemplate(String templateId);
	
	
	/**
	 * 2.获取任务状态
	 * @param taskid
	 * @param kind
	 * @return
	 */
	public String GetTaskState(String taskid,String kind);
	
	/**
	 * 3.获取报告
	 * @param taskid
	 * @param category
	 * @return
	 */
	public String GetSimulationReport(String taskid,String category);
	
	/**
	 * 3.获取仿真组件列表
	 * @return
	 */
	public String GetSimulationParts();
}
