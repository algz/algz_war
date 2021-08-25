package com.cf611.approvalCommentManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApprovalCommentRepository extends JpaSpecificationExecutor<ApprovalComment>, JpaRepository<ApprovalComment, String> {

}
