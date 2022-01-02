package com.cf611.requirementFilling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.indicatorManager.Indicator;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/requirementfilling")
public class RequirementFillingControl {

	@Autowired
	private RequirementFillingService service;
	
	/**
	 * 获取需求填充列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("/fillings")
	public ProTablePage<DefinitionView> getFillings(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam) {
		
		return service.GetFillings(pageParam,definitionParam);
	}
	
	/**
	 * 获取需求详情列表
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("definitiondetails")
	public ProTablePage<DefinitionDetail> getDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionParam){
		pageParam.setData(service.getDefinitionDetail(definitionParam));
		return pageParam;
	}
	
	/**
	 * 更新需求详情
	 * @param params
	 * @return
	 */
	@RequestMapping("updatedefinitiondetail")
	public String updateDefinitionDetail(DefinitionDetail params) {
		return service.updateDefinitionDetail(params);
	}
	
	/**
	 * 提交需求定义
	 * @param params
	 * @return
	 */
	@RequestMapping("submitdefinition")
	public String submitDefinition(Definition params) {
		return service.submitDefinition(params);
	}
	
	/**
	 * 提交需求定义
	 * @param params
	 * @return
	 */
	@RequestMapping("feedbackdefinition")
	public String feedbackDefinition(Definition params,String approvalComment) {
		return service.feedbackDefinition(params,approvalComment);
	}
	
	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	@RequestMapping("indicatorsforselect")
	public List<Indicator> getIndicatorsForSelect(Indicator indicatorParam){
		return service.getIndicatorsForSelect(indicatorParam);
	}
}
