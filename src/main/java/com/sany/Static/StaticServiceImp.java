package com.sany.Static;

import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.ftp.Client;
import com.algz.ftp.FtpClient;
import com.algz.platform.common.sql.dao.SQLRepository;
import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sany.dynamic.DynamicReponsitory;

@Service
public class StaticServiceImp implements StaticService {

	@Autowired
	private StaticReponsitory stRepository;
	
	@Autowired
	private SQLRepository service;
	
	@Override
	public String GetCondinate(String partid) {
		ObjectMapper mapper = JsonUtils.GetMapper();
		List<?> list=stRepository.GetCondinate(partid);
		JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
		ObjectNode on=mapper.createObjectNode();
		on.set("condinate", node);
		on.put("msg",list.size()==0? "没查询到相关数据。":"");
		return on.toString();
	}

	@Override
	public String ReceiveStaticGK(String partid) {
		ObjectMapper mapper = JsonUtils.GetMapper();
		List<?> list=stRepository.ReceiveStaticGK(partid);
		JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
		ObjectNode on=mapper.createObjectNode();
		on.set("gk", node);
		on.put("msg",list.size()==0? "没查询到相关数据。":"");
		return on.toString();
	}

	@Override
	public String ReceiveStaticSimulationTask(String datajson) {
		 //Client c=new Client();
		 //Client.Download("127.0.0.1", "sany", "sany", "D:/test.txt", "Template/性能参数测试模板.xlsx");
		FtpClient ftp =new FtpClient(); 
		
		ftp.username=ftp.password="sany";
		ftp.downloadFile("Template", "DataInterface.dll", "D:\\");
		
		Map<String,Object> m=JsonUtils.jsonToMap(datajson);
		Map<String,Object> taskM=(Map<String,Object>)m.get("task");
		StaticTask task=new StaticTask();
		task.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		task.setTaskName(taskM.get("taskname")+"");
		task.setEditor(taskM.get("editor")+"");
		ArrayList<String> arr=(ArrayList<String>)taskM.get("gk");
		task.setGk(String.join(", ", arr));
		task.setCadPath("");
		task.setCadName("");
		stRepository.SaveStaticTask(task);
		return null;
	}

//	@Autowired
//	private MessageReponsitory msgRepository;
//	
//	@Override
//	public void SendMessage(String msg) {
//		//{task={taskname=SR450多体动力学仿真计算, editor=0000001, gk=[dlw, ts], cadname=桅杆动力学模型, cadversion=A1.1, cadfile=ftp://127.0.0.1/桅杆201010131000.zip, cadfilepassword=1234}}
//		Map m=JsonUtils.jsonToMap(msg);
//		Map task=(Map)m.get("task");
//		
//		//msgRepository.
//	}

}
