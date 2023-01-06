package com.algz.flow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALGZ_FLOW_VIEW")
public class FlowView {

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="CREATEDATE",updatable = false,insertable = false)
	private String createDate;
	
	@Column(name="CREATORNAME")
	private String creatorName;

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	
}
