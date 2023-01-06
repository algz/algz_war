package com.cf611.requirementApproval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/requirementapproval")
public class RequirementApprovalControl {

	@Autowired
	private RequirementApprovalService service;
	
	
	/**
	 * 获取需求详情
	 * @param definitonId
	 * @return
	 */
	@RequestMapping("definitiondetails")
	public Map<String,Object> getDefinitionDetails(String definitonId){
		return service.getDefinitionDetails(definitonId);
	}
	
	/**
	 * 提交需求定义
	 * @param params
	 * @return
	 */
	@PostMapping("submitdefinition")
	public String submitDefinition(ApprovalComment params) {
		return service.submitDefinition(params);
	}
}
