package com.sany.airmodelManager;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional(readOnly = true) 
public class AirModelServiceImp implements AirModelService{

	@Autowired
	private AirModelRepository repository;
	
	@Override
	public String GetAirModels() {
		String s = "";

		ObjectMapper mapper = JsonUtils.GetMapper();//new ObjectMapper();
		try {
			List<AirModel> list=repository.getAirModelList(null);
			JsonNode node = mapper.valueToTree(list);//mapper.readTree(s);
			ObjectNode on=mapper.createObjectNode();
			on.set("airmodels", node);
			on.put("msg",list.size()==0? "没查询到相关数据。":"");
			s=on.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return s.toLowerCase();
	}

}
