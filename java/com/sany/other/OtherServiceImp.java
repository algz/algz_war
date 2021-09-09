package com.sany.other;

import java.awt.Desktop;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.common.file.pathencode.APathCode;
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
		//state,stateinfo
		List<Object[]> list=(List<Object[]>)otherRepository.GetTaskState(taskid, kind);
		String state="";
		String msg="";
		if(list==null||list.size()==0) {
			//任务没有找到
			state="异常";
			msg="任务没有找到！";
		}else {
			state=list.get(0)[0]+"";
			switch(list.get(0)[0]+"") {
				case"未发布":
				case"已发布":
				case"终版":
					state="完成";
					break;
				case"异常":
					state="完成";
					break;
				default:
					state="计算中";
					break;
			}
			msg=list.get(0)[1]+"";
		}
		ObjectNode on=mapper.createObjectNode();
		on.put("state", state);
		on.put("put", msg);//on.put("stateinfo",list.size()==0||list.get(0)[1]==null? "":list.get(0)[1]+"");
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
			String id=m.get("ID");
			String paramSQL="";
			if(id.equals("4")) {
				//工作参数
				paramSQL=" and templateId in ('12','13','14','43')";
			}else if(id.equals("3")) {
				//铰点坐标
				paramSQL=" and templateId in ('9','10','11')";
			}else if(id.equals("7")) {
				//ZXJ
				paramSQL=" and templateId in ('17','18','19','20')";
			}else if(id.equals("24")) {
				//ZXJ
				paramSQL=" and templateId in ('30','31','32')";
			}else if(id.equals("25")) {
				//ZXJ
				paramSQL=" and templateId in ('33','34','35','42')";
			}else if(id.equals("28")) {
				//ZXJ
				paramSQL=" and templateId in ('38','39','40','41')";
			}else {
				paramSQL=" and templateId='"+id+"'";
			}
			List<?> temList=service.ExecuteDataTable("select name,cname,valtype,unit from S_WHOLE_TEMPLATEPARAM_VIEW where iszhp is null and iscalc is null "+paramSQL);
			JsonNode cnode = mapper.valueToTree(temList);
			node.set(m.get("NAME"), cnode);
		}
		root.set("tempateparam", node);
		root.put("templatename",templateId.equals("0")? "平行四边形":"大三角");
		return root.toString();
	}
	
	@Override
	public APathCode GetSimulationReport(String taskid, String category) {
		return otherRepository.GetSimulationReport(taskid, category);
	}


	


}
