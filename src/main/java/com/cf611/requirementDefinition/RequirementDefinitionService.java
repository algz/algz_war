package com.cf611.requirementDefinition;

import java.util.List;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definition.DefinitionView;
import com.cf611.util.ProTablePage;

public interface RequirementDefinitionService {

	public ProTablePage<Definition> getDefinitions(ProTablePage<Definition> pageParam,Definition definitionParam);
	
	public ProTablePage<DefinitionDetail> getDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetail);
	
	/**
	 * 添加需求定义
	 * @param definition
	 * @return
	 */
	public String addDefinition(Definition definition);
	
	/**
	 * 发布需求定义，更新状态。
	 * @param definition
	 * @return
	 */
	public String publicDefinition(Definition definition);
	
	/**
	 * 删除需求定义
	 * @param definition
	 * @return
	 */
	public String delDefinition(Definition definition);
	
	/**
	 * 发布需求定义
	 * @param definitonId
	 * @return
	 */
	public String publishDefinition(Definition definition);
	
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
