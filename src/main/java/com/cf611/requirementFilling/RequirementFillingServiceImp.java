package com.cf611.requirementFilling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf611.approvalCommentManager.ApprovalCommentService;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.definitionDetailManager.DefinitionDetailService;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;

@Service
@Transactional(readOnly = true) 
public class RequirementFillingServiceImp implements RequirementFillingService {

	@Autowired
	private RequirementDefinitionService definitionService;
	
	@Autowired
	private DefinitionDetailService definitionDetailService;
	
	@Autowired
	private ApprovalCommentService approvalCommentService;
	
	@Override
	public ProTablePage<Definition> GetFillings(ProTablePage<Definition> pageParam, Definition definitionParam) {
		return definitionService.getDefinitions(pageParam, definitionParam);
	}

	/**
	 * 获取需求详情列表
	 */
	@Override
	public List<DefinitionDetail> getDefinitionDetail(DefinitionDetail definitionParam) {
		return definitionDetailService.getDefinitionDetailByDefinitionId(definitionParam.getDefinitionId());
	}

	@Transactional
	@Override
	public String updateDefinitionDetail(DefinitionDetail params) {
		definitionDetailService.updateDefinitionDetail(params);
		return null;
	}

	@Transactional
	@Override
	public String submitDefinition(Definition params) {
		definitionService.publicDefinition(params);
		return null;
	}

	@Transactional
	@Override
	public String feedbackDefinition(Definition params) {
		definitionService.publicDefinition(params);
//		approvalCommentService.saveApprovalComment(null);
		return null;
	}

}
