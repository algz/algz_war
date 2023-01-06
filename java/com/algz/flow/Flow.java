package com.algz.flow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ALGZ_FLOW")
public class Flow {


	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String flowid;
	
	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String flowname;
	
	@Column(name="CREATOR")
	private String creator;
	
	/**
	 * X state: 0待发布；1待填充；2待审签；3待判定；4反馈;5已发布
	 * state: 0待发布；1待填充；2待审签；3待判定；4待归档；5已归档；6反馈；
	 */
	@Column(name="STATE")
	private String state;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="CREATEDATE",updatable = false,insertable = false)
	private String createDate;
	


	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
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


	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
