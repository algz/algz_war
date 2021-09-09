package com.cf611.approvalCommentManager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.util.ProTablePage;

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


	/**
	 * 获取审批记录
	 */
	@Override
	public ProTablePage<ApprovalComment> getApprovalComments(ProTablePage<ApprovalComment> pageParam,ApprovalComment ac){

		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize(),
				Sort.by("createDate").descending());
		Page<Map<String,Object>> mapPage=repository.findViewByDefinitionId(ac.getDefinitionId(), pageable);
		Page<ApprovalComment> page=null;
		try {
			page = SpringBeanUtils.PageListMapToPageEntity(mapPage, pageable, ApprovalComment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageParam.setPage(page);
		return pageParam;
	}
}
