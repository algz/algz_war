/**
 * 
 */
package com.cf611.requirementDefinition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionDetail.DefinitionDetail;
import com.cf611.util.ProTablePage;


/**
 * @author algz
 *
 */
@RequestMapping("/requirementdefinition")
@RestController
public class RequirementDefinitionControl {

	@Autowired
	private RequirementDefinitionService service;
	
	@RequestMapping("/definitions")
	public ProTablePage<Definition> getDefinitions(ProTablePage<Definition> pageParam,Definition definitionParam) {
		return service.GetDefinitions(pageParam,definitionParam);
	}
	
	@RequestMapping("/definitiondetail")
	public ProTablePage<DefinitionDetail> definitionDetailService(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionParam){
		return service.GetDefinitionDetails(pageParam,definitionParam);
	}
}
