package com.sany.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sany.airmodelManager.AirModel;
import com.sany.airmodelManager.AirModelRepository;
import com.sany.whole.WholeRepository;
import com.sany.whole.WholeTask;

@Service
@Transactional(readOnly = true) 
public class DynamicServiceImp implements DynamicService {

	@Autowired
	private DynamicReponsitory dyRepository;
	
	@Autowired
	private AirModelRepository amRepository;

	@Autowired
	private WholeRepository wRepository;
	
	@Override
	public String GetDynamicGK(String datajson) {
		String s = "";

		ObjectMapper mapper = JsonUtils.GetMapper();//new ObjectMapper();
		try {
			//{"modelname":"SR320","amplitudetype":"平行四边形","compressionmode":"卷扬加压","zjywz":"前置","jyjywz":"后置"}
			Map<String, String> map = mapper.readValue(datajson, new TypeReference<CaseInsensitiveKeyMap<String>>() {
			});
			// 获取机型
			AirModel am = new AirModel();
			//am.setModelname(map.get("modelname ") + "");
			am.setAmplitudetype(map.get("amplitudetype") + "");
			am.setCompressionmode(map.get("Compressionmode")+"");
			am.setJyjywz(map.get("jyjywz") + "");
			am.setZjywz(map.get("zjywz") + "");
			List<?> list = dyRepository.GetDynamicGK(am);
			JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
			ObjectNode on=mapper.createObjectNode();
			on.set("gk", node);
			on.put("msg",list.size()==0? "没查询到相关数据。":"");
			s=on.toString();
//			Map<String,Object> m=new HashMap<String,Object>();
//			m.put("gk", list);
//			m.put("msg", "");
//			s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return s.toLowerCase();
	}

//	@Override
//	public String ReceiveDynamicSimulationTask(String datajson) {
//		//{task={taskname=SR450多体动力学仿真计算, editor=0000001, gk=[dlw, ts], cadname=桅杆动力学模型, cadversion=A1.1, cadfile=ftp://127.0.0.1/桅杆201010131000.zip, cadfilepassword=1234}}
//		ObjectMapper mapper = JsonUtils.GetMapper();
//		ObjectNode on=mapper.createObjectNode();
//		on.put("taskid", "");
//		
//		Map<String,Object>  m=JsonUtils.jsonToMap(datajson);
//		Map<String,Object> airM=(Map<String,Object>)m.get("airmodel");
//		Map<String,Object> taskM=Map.class.cast(m.get("task"));
//		Map<String,Object>  wholeM=(Map<String,Object>)m.get("wholeparam");
//		
//		if(airM==null||taskM==null||wholeM==null) {
//			on.put("msg","数据不全。");
//		}else {
//			String editor=taskM.get("editor")+"";
//			
//			AirModel am=new AirModel();
//			am.setModelname(airM.get("modelname")+"");
//			am.setAmplitudetype(airM.get("amplitudetype")+"");
//			am.setCompressionmode(airM.get("compressionmode")+"");
//			am.setZjywz(airM.get("jyjywz")+"");
//			am.setZgkind(airM.get("zg_kind")+"");
//			List<AirModel> amList=amRepository.getAirModelList(am);
//			
//			if(amList.size()>0) {
//				am=amList.get(0);
//				
//				AddWholeTask(am,editor);
//				
//				Map<String,Object>  weightfocusM=(Map)m.get("weightfocus");
//				wRepository.AddWeightFocusParam();
//				
//				Map<String,Object>  jdzbM=(Map)m.get("jdzb");
//				Map<String,Object>  jdjlM=(Map)m.get("jdjl");
//				Map<String,Object>  workparamM=(Map)m.get("workparam");
//				
//				
//				DynamicTask task=new DynamicTask();
//				task.setId(java.util.UUID.randomUUID().toString().replaceAll("-", ""));
//				task.setTaskName(taskM.get("taskName")+"");;
//				task.setModelId(taskM.get("modelId")+"");
//				task.setEditor(taskM.get("editor")+"");
//				task.setZg_type(taskM.get("zg_type")+"");
//				task.setWholeTaskId("");//taskM.get("")+"";
//				task.setState("计算中");
//				dyRepository.SaveDynamicTask(task);
////			    this.task.TaskName = drv["modelName"] + "多体动力学仿真计算";
////			    this.task.ModelId = drv["modelid"] + "";
////			    this.task.Editor = LoginUser.UserInfo.Id;
////			    this.task.ZG_TYPE = this.jsgRadioBtn.Checked ? "机锁杆" : "摩阻杆";
////			    this.task.WholeTaskId = wvb["wholetaskid"] + "";
////				task.State = "计算中";
//				
//				on.put("taskid", task.getId());
//				on.put("msg","");
//			}else {
//				on.put("msg","机型数据不正确。");
//			}
//		}
//		
//		return on.toString();
//	}

	
	private String AddWholeTask(AirModel am,String editor) {
		WholeTask wt=new WholeTask();
		wt.setId(UUID.randomUUID().toString().replace("-", ""));
		wt.setTaskName(am.getModelname()+"整机参数");
		wt.setState("已发布");
		wt.setEditor(editor);
		wt.setModelId(am.getId());
		wt.setZg_Type(am.getZgkind()=="机锁杆"?"1":am.getZgkind()=="摩阻杆"?"2":"3");
		wRepository.AddWholeTask(wt);
		return wt.getId();
	}
	
	
}
