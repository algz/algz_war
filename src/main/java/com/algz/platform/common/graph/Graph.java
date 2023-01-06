package com.algz.platform.common.graph;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ALGZ_GRAPH")
public class Graph {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String graphid;
	
	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String graphname;
	
	@Column(name = "ELEMENTID")
	private String elementid;
	
	/**
	 * 
	 */
	@Column(name = "DATA")
	private String data;


	public String getGraphid() {
		return graphid;
	}


	public void setGraphid(String graphid) {
		this.graphid = graphid;
	}


	public String getGraphname() {
		return graphname;
	}


	public void setGraphname(String graphname) {
		this.graphname = graphname;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getElementid() {
		return elementid;
	}


	public void setElementid(String elementid) {
		this.elementid = elementid;
	}
	
	
	
}
