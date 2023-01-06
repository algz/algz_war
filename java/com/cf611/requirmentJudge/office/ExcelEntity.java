package com.cf611.requirmentJudge.office;

public class ExcelEntity {

	/**	序号 */
	private String serialNo;
	
	/**	功能分类 */
	private String kindName;
	
	/**	功能语义 */
	private String semanticsName;
	
	/**	用户输入 */
	private String userIndicatorName;
	
	/**	对应指标	 */
	private String indicatorName;
	
	/**	引导判据 */
	private String regulationName;
	
	/**	仿真部件名称 */
	private String componentName;
	
	/**	仿真子模型 */
	private String subModelName;
	
	/**	子模型路径 */
	private String subModelPath;

	/**
	 * 换行 1换行，0不换行
	 */
	private String newLine;
	

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

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

	public String getNewLine() {
		return newLine;
	}

	public void setNewLine(String newLine) {
		this.newLine = newLine;
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
	
	
	
	

}
