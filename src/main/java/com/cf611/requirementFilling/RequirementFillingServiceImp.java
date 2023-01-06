package com.cf611.requirementFilling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.approvalCommentManager.ApprovalCommentService;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.definitionDetailManager.DefinitionDetailService;
import com.cf611.indicatorManager.Indicator;
import com.cf611.indicatorManager.IndicatorRepository;
import com.cf611.indicatorManager.IndicatorService;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailView;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailViewRepository;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
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
	
	@Autowired
	private IndicatorService indicatorService;
	

	
	@Override
	public ProTablePage<DefinitionView> getFillings(ProTablePage<DefinitionView> pageParam, DefinitionView definitionParam) {
		return definitionService.getDefinitionView(pageParam, definitionParam);
	}

	/**
	 * 获取需求详情列表
	 */
	@Override
	public List<DefinitionDetailView> getDefinitionDetail(DefinitionDetail definitionParam) {
		return definitionDetailService.getDefinitionDetailByDefinitionId(definitionParam.getDefinitionId());
	}

	@Transactional
	@Override
	public String updateDefinitionDetail(DefinitionDetail params) {
		definitionDetailService.updateDefinitionDetail(params);
		return null;
	}

	/**
	 * 提交到流程审批，状态修改为2。
	 */
	@Transactional
	@Override
	public String submitDefinition(Definition params) {
		ApprovalComment ac=new ApprovalComment();
		ac.setApprovalResult("1"); //1通过，0不通过
		ac.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		ac.setDefinitionId(params.getId());
		ac.setKind("1");
		
		definitionService.publishDefinition(params.getId(),"2",ac);
		return null;
	}

	/**
	 * 退回待反馈，修改状态为0.
	 */
	@Transactional
	@Override
	public String feedbackDefinition(Definition params,String approvalComment) {
		ApprovalComment ac=new ApprovalComment();
		ac.setApprovalComment(approvalComment);
		ac.setApprovalResult("0");
		ac.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		ac.setDefinitionId(params.getId());
		ac.setKind("1");
		
		definitionService.publishDefinition(params.getId(),"0",ac);
		
		return null;
	}

	@Override
	public List<Indicator> getIndicatorsForSelect(Indicator indicatorParam) {
		// TODO Auto-generated method stub
		List<Indicator> list=indicatorService.getIndicatorsList(indicatorParam);
		return list;
	}

	/**
	 * 删除需求定义(除v1版本)
	 */
	@Transactional
	@Override
	public String delDefinition(Definition params) {
		if(params.getVersion()!=null&&!"1".equals(params.getVersion())) {
			definitionService.delDefinition(params);
		}
		return null;
	}

}
