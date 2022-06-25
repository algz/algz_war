package com.algz.platform.common.graph.node;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ALGZ_GRAPH_NODE")
public class Node {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator",strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String nodeid;
	
	/**
	 * 
	 */
	@Column(name = "DATA")
	private String data;
	
	@Column(name = "NAME")
	private String name;
	

	@Column(name = "ELEMENTID")
	private String elementid;
	
	@Column(name = "GRAPHID")
	private String graphid;

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGraphid() {
		return graphid;
	}

	public void setGraphid(String graphid) {
		this.graphid = graphid;
	}

	public String getElementid() {
		return elementid;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	
	
}
