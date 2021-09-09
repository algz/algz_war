package com.cf611.requirementDefinition;

import java.util.List;

import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

public interface RequirementDefinitionService {

	public ProTablePage<DefinitionView> getDefinitions(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam);
	
	public ProTablePage<DefinitionDetail> getDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetail);
	
	/**
	 * 添加需求定义
	 * @param definition
	 * @return
	 */
	public String addDefinition(Definition definition);
	
	/**
	 * 删除需求定义
	 * @param definition
	 * @return
	 */
	public String delDefinition(Definition definition);
	
	
	/**
	 * 发布需求定义
	 * @param definitonId
	 * @param state
	 * @param approvalComment
	 * @return
	 */
	public String publishDefinition(String definitionId,String state,ApprovalComment ac);
	
	/**
	 * 通过需求定义，获取需求定义详情
	 * @param definition
	 * @return
	 */
	public List<DefinitionDetail> getDefinitionDetailByDefinitionId(String definitonId);
	
	/**
	 * 删除需求定义关联的需求定义详情
	 * @param definitionId
	 * @param definitionDetailId
	 * @return
	 */
	public String delDefinitionDetail(String definitionDetailId);

	/**
	 * 获取需求定义
	 * @param definitionId
	 * @return
	 */
	Definition getDefinition(String definitionId);
	
	
}
