package com.sany.Static.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.ftp.FtpClient;
import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sany.Static.StaticTask;

@Service
@Transactional(readOnly = true) 
public class StaticServiceImp1 implements StaticService1 {

	@Autowired
	private StaticReponsitory1 stRepository1;
	
	
	public String GetDynamicZH(String modelId) {
		String s = "";

		ObjectMapper mapper = JsonUtils.GetMapper();//new ObjectMapper();
		try {
			List<?> list = stRepository1.GetDynamicZH(modelId);
			JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
			ObjectNode on=mapper.createObjectNode();
			on.set("zh", node);
			on.put("msg",list.size()==0? "没查询到相关数据。":"");
			s=on.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return s.toLowerCase();
	}

//	public String ReceiveStaticSimulationTask(String datajson) {
//		 //Client c=new Client();
//		 //Client.Download("127.0.0.1", "sany", "sany", "D:/test.txt", "Template/性能参数测试模板.xlsx");
//		FtpClient ftp =new FtpClient(); 
//		
//		ftp.username=ftp.password="sany";
//		ftp.downloadFile("Template", "DataInterface.dll", "D:\\");
//		
//		Map<String,Object> m=JsonUtils.jsonToMap(datajson);
//		Map<String,Object> taskM=(Map<String,Object>)m.get("task");
//		StaticTask task=new StaticTask();
//		task.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		task.setTaskName(taskM.get("taskname")+"");
//		task.setPartId(taskM.get("partid")+"");
//		task.setEditor(taskM.get("editor")+"");
//		task.setCadPath(taskM.get("cadpath")+"");
//
//		task.setModelId(taskM.get("modelId")+"");
//		task.setZh_Version(taskM.get("zh_version")+"");
//
//		String CADName = st.ModelName + "_WG_" + DateTime.Now.ToString("yyyyMMddHHmmss") + "_" + st.Version;
//		task.setCadName(CADName);
//		
//		
////		st.TaskKind = "2";//1仿真平台；2设计平台；
////        st.FromSource = this.fromSource1Btn.Checked?"1":"2"; //来源: 1设计载荷(设计仿真),2试验载荷(样机仿真)
////		ArrayList<String> arr=(ArrayList<String>)taskM.get("gk");
////		task.setGk(String.join(", ", arr));
//
//		stRepository1.SaveStaticTask(task);
//		return null;
//	}
	
}
