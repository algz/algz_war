package com.cf611.requirmentDataBase.indicatorBase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.indicatorManager.Indicator;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirmentDataBase.semanticsBase.Semantics;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@RestController
@RequestMapping("/indicatorbase")
public class IndicatorBaseControl {

	@Autowired
	private IndicatorBaseService service;
	
//	/**
//	 * 获取指标库树结点
//	 * @param pageParam
//	 * @param definitionParam
//	 * @return
//	 */
//	@RequestMapping("/indicatortreenodes")
//	public List<TreeNode> getIndicatorTreeNodes(ProTablePage<Definition> pageParam,Definition definitionParam) {
//		return null;//service.GetFillings(pageParam,definitionParam);
//	}
	
	
	/**
	 * 获取指标列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("indicatortable")
	public ProTablePage<Indicator> getIndicatorTable(ProTablePage<Indicator> pageParam,Indicator indicatorParam) {
		return service.getIndicatorTable(pageParam,indicatorParam);
	}
	
	/**
	 * 获取指标列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("semanticstable")
	public ProTablePage<Semantics> getSemanticsTable(ProTablePage<Semantics> pageParam,Semantics param) {
		return service.getSemanticsTable(pageParam,param);
	}
	
	/**
	 * 获取指标
	 * @param indicatorParam
	 * @return
	 */
	@RequestMapping("/indicator")
	public Indicator getIndicator(Indicator indicatorParam) {
		return service.getIndicator(indicatorParam);
	}
	
	/**
	 * 保存指标
	 * @param indicatorParam
	 * @return
	 */
	public String saveIndicator(Indicator indicatorParam) {
		return service.saveIndicator(indicatorParam);
	}
	
	/**
	 * 删除指标
	 * @param indicatorParam
	 * @return
	 */
	public String delIndicator(Indicator indicatorParam) {
		return service.delIndicator(indicatorParam);
	}
	
	@PostMapping("addindicatorsemantics")
	public String addIndicatorSemantics(String indicatorId,String semanticsId) {
		return service.addIndicatorSemantics(indicatorId, semanticsId);
	}
	
	@PostMapping("delindicatorsemantics")
	public String delIndicatorSemantics(String indicatorId,String semanticsId) {
		return service.delIndicatorSemantics(indicatorId, semanticsId);
	}
	
	@RequestMapping("indicatorsemantics")
	public List<String> getIndicatorSemantics(String indicatorId) {
		return service.getSemanticsByIndicatorId(indicatorId);
	}
}
