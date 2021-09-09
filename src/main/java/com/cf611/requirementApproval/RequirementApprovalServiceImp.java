package com.cf611.requirementApproval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.approvalCommentManager.ApprovalCommentService;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;

@Transactional(readOnly = true)
@Service
public class RequirementApprovalServiceImp implements RequirementApprovalService {

	@Autowired
	private RequirementDefinitionService definitionService;
	
	@Autowired
	private ApprovalCommentService approvalCommentService;
	
	@Autowired
	private RequirementDefinitionService requirementDefinitionService;
	
	@Transactional
	@Override
	public String submitDefinition(ApprovalComment ac) {

		ac.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		ac.setKind("2");
		definitionService.publishDefinition(ac.getDefinitionId(),ac.getApprovalResult().equals("1") ? "3" : "1",ac);

		return null;
	}

	@Override
	public Map<String, Object> getDefinitionDetails(String definitonId) {
		Definition definition=requirementDefinitionService.getDefinition(definitonId);
		List<DefinitionDetail> list= requirementDefinitionService.getDefinitionDetailByDefinitionId(definitonId);
		ApprovalComment ac=new ApprovalComment();
		ac.setDefinitionId(definitonId);
		ac.setKind("2");
		List<ApprovalComment> acList=approvalCommentService.getApprovalCommentList(ac);
		StringBuilder str=new StringBuilder();
		
		if(!definition.getState().equals("2")&&acList.size()>0) {
			for(ApprovalComment act : acList) {
				str.append(act.getCreateDate()+":"
			+(act.getApprovalResult().equals("1")?"通过":"不通过")+":"
			+(StringUtils.isEmpty(act.getApprovalComment())?"":act.getApprovalComment())+"\n");
			}
		}else {
			str.append(acList.size()>0&&acList.get(0).getApprovalComment()!=null?acList.get(0).getApprovalComment():"");
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("data", list);
	    map.put("approvalComment", str.toString());
		return map;
	}

}
