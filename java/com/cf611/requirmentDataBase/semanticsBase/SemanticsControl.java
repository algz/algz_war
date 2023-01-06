package com.cf611.requirmentDataBase.semanticsBase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/semanticsmanager")
public class SemanticsControl {

	@Autowired
	private SemanticsService service;
	
	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	@RequestMapping("semanticss")
	public ProTablePage<Semantics> getSemanticss(ProTablePage<Semantics> pageParam,Semantics semanticsParam){
		return service.getSemanticss(pageParam, semanticsParam);
	}
	
	/**
	 * 保存语义
	 * @param semantics
	 * @return
	 */
	@RequestMapping("savesemantics")
	public String saveSemantics(Semantics semantics) {
		return service.saveSemantics(semantics);
	}
	
	/**
	 * 删除语义
	 * @param semantics
	 * @return
	 */
	@RequestMapping("delsemantics")
	public String delSemantics(Semantics semantics) {
		return service.delSemantics(semantics);
	}
	

}
