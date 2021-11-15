package com.cf611.indicatorManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CF_INDICATORS")
@DynamicInsert //insert语句时，null值不添加。
@DynamicUpdate //update语句时，null值不更新。
public class Indicator {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "CREATEDATE",insertable = false,updatable = false)
	private String createDate;
	
	@Column(name = "KEYWORD")
	private String keyword;
	
	@Column(name = "PARENTID")
	private String parentId;

	@Column(name="VALTYPE")
	private String valType;
	
	@Column(name="UNIT")
	private String unit;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

}
