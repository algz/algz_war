package com.cf611.approvalCommentManager;

import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.cf611.requirementDefinition.definitionView.DefinitionView;

public interface ApprovalCommentRepository extends JpaSpecificationExecutor<ApprovalComment>, JpaRepository<ApprovalComment, String> {

	/**
	 * 重新实现此方法
	 */
	@Query(nativeQuery = true,value="select * from CF_APPROVALCOMMENT_VIEW")
	public Page<Map<String,Object>> findViewAll(@Nullable Specification<ApprovalComment> spec, Pageable pageable);

	/**
	 * 重新实现此方法
	 */
	@Query(nativeQuery = true,value="select * from CF_APPROVALCOMMENT_VIEW where definitionId=?1")
	public Page<Map<String,Object>> findViewByDefinitionId(@Nullable String definitionId, Pageable pageable);
}
