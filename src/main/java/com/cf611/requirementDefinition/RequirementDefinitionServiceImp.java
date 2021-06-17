/**
 * 
 */
package com.cf611.requirementDefinition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definition.DefinitionRepository;
import com.cf611.requirementDefinition.definitionDetail.DefinitionDetail;
import com.cf611.requirementDefinition.definitionDetail.DefinitionDetailRepository;
import com.cf611.util.ProTablePage;

/**
 * @author algz
 *
 */
@Service
@Transactional(readOnly = true) 
public class RequirementDefinitionServiceImp implements RequirementDefinitionService {

	@Autowired
	private DefinitionRepository definitionRepository;
	
	@Autowired
	private DefinitionDetailRepository definitionDetailRepository;
	
	/**
	 * 分页查询
	 */
	@Override
	public ProTablePage<Definition> GetDefinitions(ProTablePage<Definition> pageParam,Definition definitionParam) {

		//排序
		//Sort sort= new Sort(Sort.Direction.ASC, "uid");
		//Pageable pageable = new PageRequest(pageIndex, pageSize,sort);
		
		//参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size 设置每页数据的条数
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		
		Page<Definition> page= definitionRepository.findAll(Example.of(definitionParam),pageable);
		pageParam.setPage(page);
		return pageParam;
	}

	@Override
	public ProTablePage<DefinitionDetail> GetDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetail) {
		
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		Page<DefinitionDetail> page= definitionDetailRepository.findAll(Example.of(definitionDetail),pageable);
		pageParam.setPage(page);
		return pageParam;
	}

}
