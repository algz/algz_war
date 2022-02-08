package com.cf611.requirementDefinition.definitionDetailView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CF_DEFINITIONDETAIL_VIEW")
public class DefinitionDetailView {
	
public DefinitionDetailView() {}
	
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name="DEFINITIONID",insertable = false ,updatable = false) 
	private String definitionId;
	
	/**
	 * 指标ID（值）
	 */
	@Column(name="VAL")
	private String val;
	
//	/**
//	 * 指标ID（值）
//	 */
//	@Column(name="INDICATORID")
//	private String indicatorId;
	
	/**
	 * 关联的指标名称。与val(存储的是指标ID)相关
	 */
	@Column(name="INDICATORNAME")
	private String indicatorName;
	
	@Column(name="CREATOR")
	private String creator;

	@Column(name="CREATEDATE")
	private String createDate;

	/**
	 * 语义ID
	 */
	@Column(name="SEMANTICSID")
	private String semanticsId;
	
	@Column(name="SEMANTICSNAME")
	private String semanticsName;
	
	@Column(name="SEMANTICSKINDID")
	private String semanticsKindId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
//
//	public String getIndicatorId() {
//		return indicatorId;
//	}
//
//	public void setIndicatorId(String indicatorId) {
//		this.indicatorId = indicatorId;
//	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
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

	public String getSemanticsId() {
		return semanticsId;
	}

	public void setSemanticsId(String semanticsId) {
		this.semanticsId = semanticsId;
	}

	public String getSemanticsName() {
		return semanticsName;
	}

	public void setSemanticsName(String semanticsName) {
		this.semanticsName = semanticsName;
	}

	public String getSemanticsKindId() {
		return semanticsKindId;
	}

	public void setSemanticsKindId(String semanticsKindId) {
		this.semanticsKindId = semanticsKindId;
	}
	
	
	
}
