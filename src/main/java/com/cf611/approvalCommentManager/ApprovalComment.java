/**
 * 
 */
package com.cf611.approvalCommentManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author algz
 *
 */
@Entity
@Table(name="CF_APPROVALCOMMENT")
@DynamicInsert(true) //insert语句时，null值不添加。
@DynamicUpdate(true) //update语句时，null值不更新。
public class ApprovalComment {
	
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")//org.hibernate.id.UUIDGenerator
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	@Column(name = "APPROVALCOMMENT")
	private String approvalComment;
	
	/**
	 * 1通过，0不通过
	 */
	@Column(name = "APPROVALRESULT")
	private String approvalResult;
	
	@Column(name = "DEFINITIONID")
	private String definitionId;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "CREATEDATE",updatable = false,insertable = false)
	private String createDate;
	
	/**
	 * Definition = 0, Filling = 1, Approval = 2, Regulation = 3, FeelBack = 4 
	 */
	@Column(name = "KIND")
	private String kind;

	@Column(name="RELATIONID")
	private String relationId;
	
	@Transient
	private String kindName;
	
	@Transient
    private String creatorName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String comment) {
		this.approvalComment = comment;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	

	
}
