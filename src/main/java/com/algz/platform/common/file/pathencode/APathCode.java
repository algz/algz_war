package com.algz.platform.common.file.pathencode;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.cxf.common.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="ALGZ_PATHCODE")
public class APathCode {

	
	@Id
	@GenericGenerator(name="ALGZGenerator",strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	@Column(name="ID",length=32)
	private String id;
	
	@Column(name="FILEPATH")
	private String filePath;

	@Column(name="REMARK")
	private String remark;
	
	@Column(name="RELATIONID")
	private String relationId;
	
	@Column(name="RELATIONKIND")
	private String relationKind;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="CREATEDATE",insertable = false,updatable = false)
	private String createDate;
	
	@Column(name="FILENAME")
	private String fileName;
	
	@Transient
	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;

	
	public String getFileFullPath() {
		return StringUtils.isEmpty(this.filePath)?null:filestorePath+this.filePath;
	}

	public String getFilestorePath() {
		return filestorePath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getRelationKind() {
		return relationKind;
	}

	public void setRelationKind(String relationKind) {
		this.relationKind = relationKind;
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
	
}
