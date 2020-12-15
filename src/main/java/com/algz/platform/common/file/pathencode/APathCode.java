package com.algz.platform.common.file.pathencode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	
	
}
