package com.cf611.approvalCommentManager;

import java.util.List;

import com.cf611.util.ProTablePage;

public interface ApprovalCommentService {

	public ApprovalComment getApprovalComment(ApprovalComment ac);
	
	public List<ApprovalComment> getApprovalCommentList(ApprovalComment ac);
	
	public String saveApprovalComment(ApprovalComment ac);

	ProTablePage<ApprovalComment> getApprovalComments(ProTablePage<ApprovalComment> pageParams,
			ApprovalComment acParams);
}
