package com.cf611.regulationManager;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.RequestBody;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="CF_REGULATION")
public class Regulation {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	/**
	 * 指标ID
	 */
	@Column(name="INDICATORID")
	private String indicatorId;
	
	/**
	 * 组件ID
	 */
	@Column(name="MODELID")
	private String modelId;
	
	/**
	 * 判定规则
	 */
	@Column(name="REGULATIONVAL")
	private String regulationVal;
	
	@Transient
	private String indicatorName;

	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //解决无法接收前端参数,request请求的参数名前必须加 @RequestBody
	private List<Regulation> regulationList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getRegulationVal() {
		return regulationVal;
	}

	public void setRegulationVal(String regulationVal) {
		this.regulationVal = regulationVal;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public List<Regulation> getRegulationList() {
		return regulationList;
	}

	public void setRegulationList(List<Regulation> regulationList) {
		this.regulationList = regulationList;
	}
	
	
}
