package com.cf611.requirmentJudge.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name="CF_DEFINITION_REGULATION_VIEW")
@Entity
@IdClass(DefintionRegulationKey.class)/**重点，注解@IdClass标注用于标注实体所使用主键规则的类**/
public class DefinitionRegulationView {
	
	@Id
	@Column(name="REGULATIONID")
	private String regulationId;
	
	@Id
	@Column(name="INDICATORID")
	private String indicatorId;
	
	@Id
	@Column(name="DEFINITIONID")
	private String definitionId;
	
	@Column(name="KINDNAME")
	private String kindName;
	
	@Column(name="SEMANTICSNAME")
	private String semanticsName;
	
	@Column(name="USERINDICATORNAME")
	private String userIndicatorName;
	
	@Column(name="INDICATORNAME")
	private String indicatorName;
	
	@Column(name="REGULATIONNAME")
	private String regulationName;
	
	@Column(name="COMPONENTNAME")
	private String componentName;
	
	@Column(name="SUBMODELNAME")
	private String subModelName;
	
	@Column(name="SUBMODELPATH")
	private String subModelPath;
	


	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getSemanticsName() {
		return semanticsName;
	}

	public void setSemanticsName(String semanticsName) {
		this.semanticsName = semanticsName;
	}

	public String getUserIndicatorName() {
		return userIndicatorName;
	}

	public void setUserIndicatorName(String userIndicatorName) {
		this.userIndicatorName = userIndicatorName;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getRegulationName() {
		return regulationName;
	}

	public void setRegulationName(String regulationName) {
		this.regulationName = regulationName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getSubModelName() {
		return subModelName;
	}

	public void setSubModelName(String subModelName) {
		this.subModelName = subModelName;
	}



	public String getSubModelPath() {
		return subModelPath;
	}

	public void setSubModelPath(String subModelPath) {
		this.subModelPath = subModelPath;
	}

	public String getRegulationId() {
		return regulationId;
	}

	public void setRegulationId(String regulationId) {
		this.regulationId = regulationId;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	
	
}
