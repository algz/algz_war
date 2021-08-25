package com.cf611.approvalCommentManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ApprovalCommentServiceImp implements ApprovalCommentService{

	
	@Autowired
	private ApprovalCommentRepository repository;
	
	@Transactional
	@Override
	public String saveApprovalComment(ApprovalComment ac) {
		//repository.findOne(Example.of((ac));
		repository.save(ac);
		return null;
	}

	@Override
	public ApprovalComment getApprovalComment(ApprovalComment ac) {
		return repository.findOne(Example.of(ac)).get();
	}

	@Override
	public List<ApprovalComment> getApprovalCommentList(ApprovalComment ac) {
		return repository.findAll(Example.of(ac));
	}

}
