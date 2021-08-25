/**
 * 
 */
package com.cf611.requirementDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.indicatorManager.IndicatorService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definition.DefinitionView;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;


/**
 * @author algz
 *
 */
@RequestMapping("/requirementdefinition")
@RestController
public class RequirementDefinitionControl {

	@Autowired
	private RequirementDefinitionService service;
	
	@Autowired
	private IndicatorService indicatorService;
	
	/**
	 * 获取需求定义列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("definitions")
	public ProTablePage<Definition> getDefinitions(HttpServletRequest request,ProTablePage<Definition> pageParam,Definition definitionParam) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    String name = auth.getName(); //get logged in userna
		String csrf=request.getHeader("X-CSRF-TOKEN");
		request.getSession().setAttribute("CsrfToken", csrf);
		return service.getDefinitions(pageParam,definitionParam);
	}
	
	
	/**
	 * 添加需求定义
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="/adddefinition",method=RequestMethod.POST)
	public String addDefinition(@RequestBody Definition definition) {
		return service.addDefinition(definition);
	}
	
	/**
	 * 发布需求定义
	 * @param definition
	 * @return
	 */
	@PostMapping("/publicdefinition")
	public String publicDefinition(@RequestBody Definition definition) {
		return service.publicDefinition(definition);
	}
	
	/**
	 * 删除需求定义
	 * @param definition
	 * @return
	 */
	@PostMapping("/deldefinition")
	public String delDefinition(@RequestBody Definition definition) {
		return service.delDefinition(definition);
	}
	
	/**
	 * 获取需求定义详情
	 * @param definitonId
	 * @return
	 */
	@RequestMapping("/definitiondetails")
	public List<DefinitionDetail> getDefinitionDetails(String definitonId){
		return service.getDefinitionDetailByDefinitionId(definitonId);
	}
	

	
	@PostMapping("/deldefinitiondetail")  // definitionDetailId
	public String delDefinitionDetail(String definitionDetailId) {
		return service.delDefinitionDetail(definitionDetailId);
	}
	
	@RequestMapping("/editdefinition")
	public Map<String,Object> editDefinition(HttpServletRequest request){
		String key=request.getParameter("key");
		String definitonId=request.getParameter("definitionId");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("indicator", indicatorService.GetIndicatorNodes(new TreeNode(key)));
		map.put("definitionDetail", service.getDefinitionDetailByDefinitionId(definitonId));
		
		return map;
	}
	
	@RequestMapping("/definition")
	public List<Definition> getDefinition(Definition definition){
		return null;
	}
	
//	@PostMapping("/updatedefinition")
//	public String updateDefinition(@RequestBody Definition definition) {
//		return "";
//	}
}
