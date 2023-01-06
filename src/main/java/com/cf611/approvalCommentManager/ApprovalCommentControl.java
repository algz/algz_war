package com.cf611.approvalCommentManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/approvalcommentmanger")
public class ApprovalCommentControl {

	@Autowired
	private ApprovalCommentService service;
	
	@RequestMapping("approvalcomments")
	public ProTablePage<ApprovalComment> getApprovalComments(ProTablePage<ApprovalComment> pageParams,ApprovalComment acParams){
		return service.getApprovalComments(pageParams, acParams);
	}
}
