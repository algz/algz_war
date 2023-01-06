package com.cf611.requirementApproval;

import java.util.Map;

import com.cf611.approvalCommentManager.ApprovalComment;

public interface RequirementApprovalService {
	
	public String submitDefinition(ApprovalComment params);
	
	public Map<String,Object> getDefinitionDetails(String definitonId);
}
