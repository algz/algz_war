package com.cf611.requirmentJudge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/requirementjudge")
public class RequirementJudgeController {

	@Autowired
	private RequirementJudgeService service;
	
	/**
	 * 获取需求填充列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("/judges")
	public ProTablePage<DefinitionView> getJudges(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam) {
		
		return service.GetJudges(pageParam,definitionParam);
	}
	
	/**
	 * 接收需求任务
	 * @param params
	 * @return
	 */
	@RequestMapping("receivedefinition")
	public String receiveDefinition(Definition params) {
		return service.receiveDefinition(params);
	}
}
