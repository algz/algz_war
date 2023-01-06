package com.algz.platform.common.graph;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程图形管理
 * @author algz
 *
 */
@RestController
@RequestMapping("/common/graph")
public class GraphControl {
	
	@Autowired
	private GraphService service;
	
	
	
	/**
	 * 上传图形数据
	 * @param cells
	 * @param graphid
	 * @return
	 */
	@RequestMapping("/uploadgraphdata")
	private String uploadGraphData(String cells,String elementid) {
		service.saveGraphData(cells,elementid);
		return null;
	}

	/**
	 * 下载图形数据
	 * @param graphid
	 * @return
	 */
	@RequestMapping("/downloadgraphdata")
	private String downloadFlowData(String elementid) {
		String str= service.loadGraphData(elementid);
		return str;
	}
}
