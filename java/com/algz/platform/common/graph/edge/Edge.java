package com.algz.platform.common.graph.edge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ALGZ_GRAPH_EDGE")
public class Edge {


	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String edgeid;
	
	@Column(name = "ELEMENTID")
	private String elementid;
	
	/**
	 * 
	 */
	@Column(name = "DATA")
	private String data;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "SOURCE")
	private String source;
	
	@Column(name = "TARGET")
	private String target;
	
	@Column(name = "GRAPHID")
	private String graphid;

	public String getEdgeid() {
		return edgeid;
	}

	public void setEdgeid(String edgeid) {
		this.edgeid = edgeid;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
