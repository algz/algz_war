/**
 * 
 */
package com.sany.webservice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algz.amqp.ProducerSend;
import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sany.Static.StaticService;
import com.sany.Static.other.StaticService1;
import com.sany.airmodelManager.AirModelService;
import com.sany.dynamic.DynamicService;
import com.sany.other.OtherService;

//import net.sf.jni4net.Bridge;

//import net.sf.jni4net.Bridge;

/**
 * @author algz
 * 访问方式：http://127.0.0.1:8080/algz/common/webservice/DesignPlatformService?wsdl
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" ReceiveDynamicGK:\n in:"+datajson);
		try {
			ret = ds.GetDynamicGK(datajson);
		}catch(Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		System.out.println(" out:"+ret);
		return ret;
	}

	/**
	 * 2.获取坐标系列表√
	 */
	@Override
	public String GetCondinate(String partid) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" GetCondinate:"+partid);
		String outinfo=ss.GetCondinate(partid);
		System.out.println(" out:"+outinfo);
		return ss.GetCondinate(partid);
	}
	
	/**
	 * 3.获取静力学工况列表√
	 */
	@Override
	public String ReceiveStaticGK(String partid) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" ReceiveStaticGK:"+partid);
		String ret=ss.ReceiveStaticGK(partid);
		System.out.println(" out:"+ret);
		return ret;
	}
	
	/**
	 * 4.获取任务状态√
	 */
	@Override
	public String GetTaskState(String taskid, String kind) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String outinfo=os.GetTaskState(taskid, kind);
		System.err.println(sdf.format(new Date())+" GetTaskState:\n in"+taskid+ " "+kind+"\nout:"+outinfo);
		return outinfo;
	}
	
	/**
	 * 5.获取仿真组件列表√
	 */
	@Override
	public String GetSimulationParts() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String outinfo=os.GetSimulationParts();
		System.out.println(sdf.format(new Date())+" GetSimulationParts:\n in: \n out:"+outinfo);
		return outinfo;
	}
	
	/**
	 * 6.获取整机参数模板√
	 */
	@Override
	public String ReceiveWholeParamTemplate(String templateId) {
		String outinfo=os.ReceiveWholeParamTemplate(templateId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" ReceiveWholeParamTemplate:\nin:"+templateId+"\nout:"+outinfo);
		return outinfo;
	}
	

	/**
	 *  7.接收动力学仿真计算任务 √
	 */
	@Override
	public String ReceiveDynamicSimulationTask(String datajson) {
//		mqSend.sendMsgByTopics("queue.dynamic", "");
		//String 
		//file="C:\\Users\\algz\\Desktop\\refDll\\dll\\tem\\JniInterfaceClass.j4n.dll";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.err.println(sdf.format(new Date())+" ReceiveDynamicSimulationTask:\nin:"+datajson);
		String file=dllDirPath+"\\JniInterfaceClass.j4n.dll";
		String str="";
//        Bridge.setVerbose(true);
//        try {
//			Bridge.init();
//	        File loadDll = new File(file);
//	        Bridge.LoadAndRegisterAssemblyFrom(loadDll);
//	        str=jniinterfaceclass.JniInvokClass.AddDynamicTask(datajson);//AddWholeTask("123");//.InvokMethod("test111");
//	        System.out.println(str);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
        
        System.out.println("out:"+str);
        //
        //
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
//        Bridge.setVerbose(true);
        String str="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.err.println(sdf.format(new Date())+" ReceiveStaticSimulationTask:\nin:"+datajson);
//        try {
//			Bridge.init();
//	        File loadDll = new File(file);
//	        Bridge.LoadAndRegisterAssemblyFrom(loadDll);
//	        str=jniinterfaceclass.JniInvokClass.AddStaticTask(datajson);//AddWholeTask("123");//.InvokMethod("test111");
//	        System.err.println(str);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
        System.err.println("out:"+str);
		//String str=com.algz.FromCSharpDll.invokCSharpMethod("D:\\Source\\java\\eclipse-workspace\\Jni4netProject\\ref\\JniInterfaceClass.j4n.dll", "");
		//String taskid = ds.ReceiveDynamicSimulationTask(datajson);
        //
        //
		return str;
		
		//return ss.ReceiveStaticSimulationTask(datajson);
	}

	/**
	 * 9.获取报告
	 */
	@Override
	public String GetSimulationReport(String taskid, String category) {
		ObjectMapper mapper =JsonUtils.GetMapper();
		ObjectNode on=mapper.createObjectNode();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.err.println(sdf.format(new Date())+" GetTaskState:\nin:"+taskid+","+category);
		APathCode code=os.GetSimulationReport(taskid, category);
		
		if(code!=null) {
			File f=new File("c:"+code.getFilePath());
			String fileName=f.getName().replaceAll("{.}[^.]+$", "");
			//
			//
			//
			String info="{'reporttFileCode':'"+code.getId()+"','reportFileName':'"+fileName+"','description':'"+(code.getRemark()==null?"":code.getRemark())+"','msg':''}'";
			System.err.println("out:"+info);
			return info;
		}else {
			String s="{'reporttFileCode':'','reportFileName':'','description':'','msg':'报告不存在!'}";
			System.err.println("out:"+s);
			return s;
		}
	}
	
	@Autowired
	private AirModelService airmodelService;
	
	/**
	 * 获得机型列表
	 * @return
	 */
	public String GetAirModels() {
		String ret = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" GetAirModels:");
		try {
			ret = airmodelService.GetAirModels();
		}catch(Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		System.out.println(" out:"+ret);
		return ret;
	}

	@Autowired
	private StaticService1 st1;
	
	/**
	 * 获取动力学载荷
	 * @param modelId 机型ID
	 * @return
	 */
	public String GetDynamicZH(String modelId) {
		String ret = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+" GetDynamciZH:\n in:"+modelId);
		try {
			ret = st1.GetDynamicZH(modelId);
		}catch(Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		System.out.println(" out:"+ret);
		return ret;
	}


	public String ReceiveStaticSimulationTaskForOther(String datajson) {
		String file=dllDirPath+"\\JniInterfaceClass.j4n.dll";
//      Bridge.setVerbose(true);
      String str="";
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      System.err.println(sdf.format(new Date())+" ReceiveStaticSimulationTask:\nin:"+datajson);
//      try {
////			Bridge.init();
////	        File loadDll = new File(file);
////	        Bridge.LoadAndRegisterAssemblyFrom(loadDll);
////	        str=jniinterfaceclass.JniInvokClass.AddStaticTask(datajson);//AddWholeTask("123");//.InvokMethod("test111");
////	        System.err.println(str);
//		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
//      str=st1.ReceiveStaticSimulationTask(datajson);
      System.err.println("out:"+str);
		//String str=com.algz.FromCSharpDll.invokCSharpMethod("D:\\Source\\java\\eclipse-workspace\\Jni4netProject\\ref\\JniInterfaceClass.j4n.dll", "");
		//String taskid = ds.ReceiveDynamicSimulationTask(datajson);
      //
      //
		return str;
		
		//return ss.ReceiveStaticSimulationTask(datajson);
	}
}
