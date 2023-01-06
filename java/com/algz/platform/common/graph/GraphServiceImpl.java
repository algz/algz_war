package com.algz.platform.common.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.algz.platform.common.graph.edge.Edge;
import com.algz.platform.common.graph.edge.EdgeRepository;
import com.algz.platform.common.graph.node.Node;
import com.algz.platform.common.graph.node.NodeRepository;
import com.algz.platform.utility.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class GraphServiceImpl implements  GraphService{

	@Autowired
	private GraphRepository repository;
	
	@Autowired
	private EdgeRepository edgeRepository;
	
	@Autowired
	private NodeRepository nodeRepository;

	@Transactional
	@Override
	public String saveGraphData(String cells,String elementid) {
		ObjectMapper mapper=new ObjectMapper();
		try {
			JsonNode cellNode=mapper.readTree(cells);

			Graph graph=new Graph();
			graph.setElementid(elementid);
			Optional<Graph> op=repository.findOne(Example.of(graph));
			if(cellNode.get("cells").size()==0) {
				repository.delete(op.get());
				return null;
			}
			if(elementid.isEmpty()||!op.isPresent()) {
				//新建
				graph.setElementid(elementid);
				repository.save(graph);
			}else {
				//修改
				graph=op.get();

				
				List<Edge> edgeList=new ArrayList<>();
				Edge edge=new Edge();
				edge.setGraphid(graph.getGraphid());
				edgeRepository.deleteEdgeByGraphid(graph.getGraphid());
//				edgeList.add(edgeRepository.findOne(Example.of(edge)).get());
//				edgeRepository.deleteAllInBatch(edgeList);

				List<Node> nodeList=new ArrayList<>();
				Node node=new Node();
				node.setGraphid(graph.getGraphid());
				nodeRepository.deleteNodeByGraphid(graph.getGraphid());
//				nodeList.add(nodeRepository.findOne(Example.of(node)).get());
//				nodeRepository.deleteAllInBatch(nodeList);

			}
			
			Iterator<JsonNode> it=cellNode.get("cells").iterator();
			while(it.hasNext()) {
				JsonNode n=it.next();
				switch(n.get("shape").asText()) {
				case "edge":
					Edge edge=new Edge();
					edge.setSource(n.get("source").get("cell").asText());
					edge.setTarget(n.get("target").get("cell").asText());//18dc6fd5-e430-4eaf-9cf4-3ab93ad2727f
					edge.setElementid(n.get("id").asText());//d87e8601-e58f-4838-aa0b-6e0788230d3d
					edge.setData(n.toString());//{"shape":"edge","attrs":{"line":{"stroke":"#A2B1C3","targetMarker":{"name":"block","width":12,"height":8}}},"id":"d87e8601-e58f-4838-aa0b-6e0788230d3d","zIndex":0,"source":{"cell":"f77b5936-e3fc-4118-a26b-878aa6994043","port":"86167ae2-2c1b-49c5-ac35-b1069975e5fb"},"target":{"cell":"18dc6fd5-e430-4eaf-9cf4-3ab93ad2727f","port":"ba72d16f-dc49-4a50-a0ea-64c58aa19905"}}
					edge.setGraphid(graph.getGraphid());
					edgeRepository.save(edge);
					break;
				case "custom-rect":
					Node node=new Node();
					node.setElementid(n.get("id").asText());
					//findPath 向多层查询结点
					node.setName(n.findPath("text").findPath("text").asText());
					node.setData(n.toString());
					node.setGraphid(graph.getGraphid());
					nodeRepository.save(node);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		} 
		
		return null;
	}

	@Override
	public String loadGraphData(String elementid) {
		ObjectMapper mapper=new ObjectMapper();
		ObjectNode objectNode=mapper.createObjectNode();
		ArrayNode arrNode=mapper.createArrayNode();
		objectNode.set("cells", arrNode);
		try {
			Graph graph=new Graph();
			graph.setElementid(elementid);
			graph=repository.findOne(Example.of(graph)).get();
			String graphid=graph.getGraphid();
			
			Node node=new Node();
			node.setGraphid(graphid);
			List<Node> nodeList=nodeRepository.findAll(Example.of(node));
			for(Node n:nodeList) {
//				String s=java.net.URLDecoder.decode(n.getData());
				JsonNode jo=mapper.readTree(n.getData());
				arrNode.add(jo);
			}
			
			Edge edge=new Edge();
			edge.setGraphid(graphid);;
			List<Edge> edgeList=edgeRepository.findAll(Example.of(edge));
			for(Edge ed:edgeList) {
				arrNode.add(mapper.readTree(ed.getData()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return objectNode.toPrettyString();
	}
	
	
}
