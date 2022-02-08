package com.algz.platform.common.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algz.platform.utility.JsonUtils;

public class Flow {

	public List<Edge> edgeList;
	
	public List<Node> nodeList;

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	
	
	public static Flow toFlow(String flowString) {
		Flow f=new Flow();
		f.setEdgeList(new ArrayList<Edge>());
		f.setNodeList(new ArrayList<Node>());
		Map<String,Object> map=JsonUtils.jsonToMap(flowString);
		ArrayList<Map<String,Object>> cellList=(ArrayList<Map<String,Object>>)map.get("cells");
		for(Map<String,Object> cell : cellList) {
			
		}
		return f;
	}
}
