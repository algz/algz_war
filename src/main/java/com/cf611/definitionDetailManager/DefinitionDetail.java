package com.cf611.definitionDetailManager;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.requirementDefinition.definition.Definition;

@Entity
@Table(name="CF_DEFINITIONDETAIL")
@DynamicInsert //insert语句时，null值不添加。
@DynamicUpdate //update语句时，null值不更新。
public class DefinitionDetail {

	public DefinitionDetail() {}
	
//	public DefinitionDetail(String id,String val,String creator,String createDate,String indicatorId,
//			String definitionId,String state,String version,String definitionName,String indicatorName,
//			String indicatorParentId,String indicatorParentName) {
//		this.id=id;
//		this.val=val;
//		this.creator=creator;
//		this.createDate=createDate;
//		this.indicatorId=indicatorId;
//		this.definitionId=definitionId;
//		this.state=state;
//		this.version=version;
//		this.definitionName=definitionName;
//		this.indicatorName=indicatorName;
//		this.indicatorParentId=indicatorParentId;
//		this.indicatorParentName=indicatorParentName;
//	}
	
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")//org.hibernate.id.UUIDGenerator
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	@Column(name="VAL")
	private String val;
	
	@Column(name="CREATOR")
	private String creator;

	@Column(name="CREATEDATE")
	private String createDate;
	
//	@Column(name="INDICATORID")
//	private String indicatorId;
	
	/**
	 * 语义ID
	 */
	@Column(name="SEMANTICSID")
	private String semanticsId;
	
	/**
	 * 指标ID（值）
	 */
	@Column(name="INDICATORID")
	private String indicatorId;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="DEFINITIONID")
	private String definitionId;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	//name="字段名"；映射重复的列，必须其它重复列的属性设置insertable = false ,updatable = false，让Hibernate知道引用哪值。
    @JoinColumn(name="DEFINITIONID",insertable = false ,updatable = false) 
	private Definition defintion;

	@Transient
	private String definitionName;
	
	@Transient
	private String semanticsName;

	@Transient
	private String indicatorParentId;
	
	@Transient
	private String indicatorParentName;
	
	@Transient
	private String indicatorValType;
	
	/**
	 * 关联的指标名称。与val(存储的是指标ID)相关
	 */
	@Transient
	private String indicatorName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
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

//	public String getIndicatorId() {
//		return indicatorId;
//	}
//
//	public void setIndicatorId(String indicatorId) {
//		this.indicatorId = indicatorId;
//	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDefinitionName() {
		return definitionName;
	}

	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}


	
	public String getSemanticsName() {
		return semanticsName;
	}

	public void setSemanticsName(String semanticsName) {
		this.semanticsName = semanticsName;
	}

	@Override
	public boolean equals(Object obj) {
		String id=((DefinitionDetail)obj).id;
		return id==null?false:id.equals(this.id);
	}

	public String getIndicatorParentId() {
		return indicatorParentId;
	}

	public void setIndicatorParentId(String indicatorParentId) {
		this.indicatorParentId = indicatorParentId;
	}

	public String getIndicatorParentName() {
		return indicatorParentName;
	}

	public void setIndicatorParentName(String indicatorParentName) {
		this.indicatorParentName = indicatorParentName;
	}

	public Definition getDefintion() {
		return defintion;
	}

	public void setDefintion(Definition defintion) {
		this.defintion = defintion;
	}

	public String getIndicatorValType() {
		return indicatorValType;
	}

	public void setIndicatorValType(String indicatorValType) {
		this.indicatorValType = indicatorValType;
	}

	public String getSemanticsId() {
		return semanticsId;
	}

	public void setSemanticsId(String semanticsId) {
		this.semanticsId = semanticsId;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	
	 
}
