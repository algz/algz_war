package com.cf611.approvalCommentManager;

import java.util.List;

public interface ApprovalCommentService {

	public ApprovalComment getApprovalComment(ApprovalComment ac);
	
	public List<ApprovalComment> getApprovalCommentList(ApprovalComment ac);
	
	public String saveApprovalComment(ApprovalComment ac);
}
