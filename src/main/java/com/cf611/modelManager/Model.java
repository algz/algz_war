package com.cf611.modelManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CF_MODEL")
@DynamicInsert(true) //insert语句时，null值不添加。同时需设置一个@Column的insertable=false属性，才能全局生效。
@DynamicUpdate(true) //update语句时，null值不更新。
public class Model {
	
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PICPATH")
	private String picPath;
	
	@Column(name="FILEPATH")
	private String filePath;

	@Column(name="DESCRIPTION")
	private String description;
	
	@Transient
	private String picName;
	
	@Column(name="FILENAME")
	private String fileName;
	
	@Column(name="CREATEDATE",insertable = false,updatable = false)
	private String createDate;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="KINDID")
	private String kindId;
	
	@Column(name="COMPONENTNAME")
	private String componentName;
	
	@Column(name="SUBMODELNAME")
	private String submodelName;
	
	/**
	 * 类别：知识组件，通用成品
	 */
	@Column(name="CATEGORY")
	private String category;
	
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getSubmodelName() {
		return submodelName;
	}

	public void setSubmodelName(String submodelName) {
		this.submodelName = submodelName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
