package com.sany.other;

import java.awt.Desktop;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.common.sql.dao.SQLRepository;
import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sany.dynamic.DynamicReponsitory;

@Service
@Transactional
public class OtherServiceImp implements OtherService {

	@Autowired
	private OtherReponsitory otherRepository;
	
	@Autowired
	private SQLRepository service;

	@Override
	public String GetTaskState(String taskid, String kind) {
		ObjectMapper mapper = JsonUtils.GetMapper();
		List<Object[]> list=(List<Object[]>)otherRepository.GetTaskState(taskid, kind);
		ObjectNode on=mapper.createObjectNode();
		String str=list.get(0)[0].toString();
		on.put("state", str);
		on.put("stateinfo",list.size()==0||list.get(0)[1]==null? "":list.get(0)[1]+"");
		return on.toString();
	}

	@Override
	public String GetSimulationParts() {
		List<?> list=otherRepository.GetSimulationParts();
		ObjectMapper mapper = JsonUtils.GetMapper();
		JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
		ObjectNode on=mapper.createObjectNode();
		on.set("part", node);
		on.put("msg",list.size()==0? "没查询到相关数据。":"");
		return on.toString();
	}

	@Override
	public String ReceiveWholeParamTemplate(String templateId) {
		ObjectMapper mapper = JsonUtils.GetMapper();
		ObjectNode root=mapper.createObjectNode();
		ObjectNode node=mapper.createObjectNode();
		List<?> tList=service.ExecuteDataTable("select * from S_WHOLE_TEMPLATE_view where name is not null and parentid='"+templateId+"'");
		for(Object obj :tList) {
			Map<String,String> m=(Map<String,String>)obj;
			
			List<?> temList=service.ExecuteDataTable("select name,cname,valtype,unit from S_WHOLE_TEMPLATEPARAM_VIEW where templateId='"+m.get("ID")+"'");
			JsonNode cnode = mapper.valueToTree(temList);
			node.set(m.get("NAME"), cnode);
		}
		root.set("tempateparam", node);
		root.put("templatename",templateId.equals("0")? "平行四边形":"大三角");
		return root.toString();
	}
	
	@Override
	public String GetSimulationReport(String taskid, String category) {
		return otherRepository.GetSimulationReport(taskid, category);
	}


	


}
