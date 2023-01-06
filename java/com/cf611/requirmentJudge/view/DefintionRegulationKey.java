package com.cf611.requirmentJudge.view;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 必须 Serializable
 * @author algz
 *
 */
public class DefintionRegulationKey implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5460148842943646980L;

	@Column(name="REGULATIONID")
	private String regulationId;
	
	@Column(name="INDICATORID")
	private String indicatorId;
	
	@Column(name="DEFINITIONID")
	private String definitionId;

	
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
	
	

//    /**重点,重写hashCode 和equals方法**/
//    @Override
//    public int hashCode() {
//        final int PRIME = 31;
//        int result = 1;
//        /**重点,根据自己的主键修改**/
//        result = PRIME * result + ((roleId == null) ? 0 : roleId.hashCode());
//        result = PRIME * result + ((templateId == null) ? 0 : templateId.hashCode());
//        /**重点,根据自己的主键修改**/
//        return result;
//    }
// 
//    @Override
//    public boolean equals(Object obj){
//        if(this == obj){
//            return true;
//        }
//        if(obj == null){
//            return false;
//        }
//        if(getClass() != obj.getClass()){
//            return false;
//        }
//        final TemplateProjectRoleKey other = (TemplateProjectRoleKey)obj;
//        /**重点,根据自己的主键修改**/
//        if(roleId == null){
//            if(other.roleId != null){
//                return false;
//            }
//        }else if(!roleId.equals(other.roleId)){
//            return false;
//        }
//        if(templateId == null){
//            if(other.templateId != null){
//                return false;
//            }
//        }else if(!templateId.equals(other.templateId)){
//            return false;
//        }
//        /**重点,根据自己的主键修改**/
//        return true;
//    }

}
