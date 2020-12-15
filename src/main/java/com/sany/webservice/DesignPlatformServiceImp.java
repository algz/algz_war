/**
 * 
 */
package com.sany.webservice;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.jws.WebService;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algz.amqp.ProducerSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sany.Static.StaticService;
import com.sany.airmodelManager.AirModel;
import com.sany.dynamic.DynamicService;
import com.sany.other.OtherService;

import net.sf.jni4net.Bridge;

/**
 * @author algz
 *
 */
@WebService(serviceName = "DesignPlatformService", // 对外发布的服务名称.(可省略,省略后服务名是类名,不建议省略)与接口中指定的name一致
		targetNamespace = "http://webservice.algz.com/"// , //自定义的命名空间，通常使用包名反转。 (可省略)与接口中的命名空间一致,一般是接口的包名倒写
//        endpointInterface = "com.algz.webservice.CommonServiceA"// 服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口。(可省略)接口地址
)
@Component
public class DesignPlatformServiceImp implements DesignPlatformService {

	@Value("${algz.jni.dllDirPath:}")
	private String dllDirPath;
	
	@Autowired
	private ProducerSend mqSend; // 使用RabbitTemplate,这提供了接收/发送等等方法

	@Autowired
	private DynamicService ds;

	
	@Autowired
	private StaticService ss;
	
	@Autowired
	private OtherService os;

	/**
	 * 1.获取动力学工况列表 √
	 */
	@Override
	public String ReceiveDynamicGK(String datajson) {
		String ret = "";
		ret = ds.GetDynamicGK(datajson);
		return ret;
	}

	/**
	 * 2.获取坐标系列表√
	 */
	@Override
	public String GetCondinate(String partid) {
		return ss.GetCondinate(partid);
	}
	
	/**
	 * 3.获取静力学工况列表√
	 */
	@Override
	public String ReceiveStaticGK(String partid) {
		return ss.ReceiveStaticGK(partid);
	}
	
	/**
	 * 4.获取任务状态√
	 */
	@Override
	public String GetTaskState(String taskid, String kind) {
		return os.GetTaskState(taskid, kind);
	}
	
	/**
	 * 5.获取仿真组件列表√
	 */
	@Override
	public String GetSimulationParts() {
		return os.GetSimulationParts();
	}
	
	/**
	 * 6.获取整机参数模板√
	 */
	@Override
	public String ReceiveWholeParamTemplate(String templateId) {
		return os.ReceiveWholeParamTemplate(templateId);
	}
	

	/**
	 *  7.接收动力学仿真计算任务 √
	 */
	@Override
	public String ReceiveDynamicSimulationTask(String datajson) {
//		mqSend.sendMsgByTopics("queue.dynamic", "");
		//String file="C:\\Users\\algz\\Desktop\\refDll\\dll\\tem\\JniInterfaceClass.j4n.dll";
		String file=dllDirPath+"\\JniInterfaceClass.j4n.dll";
        Bridge.setVerbose(true);
        String str="";
        try {
			Bridge.init();
	        File loadDll = new File(file);
	        Bridge.LoadAndRegisterAssemblyFrom(loadDll);
	        str=jniinterfaceclass.JniInvokClass.AddDynamicTask(datajson);//AddWholeTask("123");//.InvokMethod("test111");
	        System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String str=com.algz.FromCSharpDll.invokCSharpMethod("D:\\Source\\java\\eclipse-workspace\\Jni4netProject\\ref\\JniInterfaceClass.j4n.dll", "");
		//String taskid = ds.ReceiveDynamicSimulationTask(datajson);
		return str;
	}

	/**
	 *  8.接收静力学仿真计算任务 √
	 */
	@Override
	public String ReceiveStaticSimulationTask(String datajson) {
		String file=dllDirPath+"\\JniInterfaceClass.j4n.dll";
        Bridge.setVerbose(true);
        String str="";
        try {
			Bridge.init();
	        File loadDll = new File(file);
	        Bridge.LoadAndRegisterAssemblyFrom(loadDll);
	        str=jniinterfaceclass.JniInvokClass.AddStaticTask(datajson);//AddWholeTask("123");//.InvokMethod("test111");
	        System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String str=com.algz.FromCSharpDll.invokCSharpMethod("D:\\Source\\java\\eclipse-workspace\\Jni4netProject\\ref\\JniInterfaceClass.j4n.dll", "");
		//String taskid = ds.ReceiveDynamicSimulationTask(datajson);
		return str;
		
		//return ss.ReceiveStaticSimulationTask(datajson);
	}

	/**
	 * 获取报告
	 */
	@Override
	public String GetSimulationReport(String taskid, String category) {
		return os.GetSimulationReport(taskid, category);
	}







}
