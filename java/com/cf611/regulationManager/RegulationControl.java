package com.cf611.regulationManager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirmentDataBase.modelBase.Model;
import com.cf611.requirmentDataBase.modelBase.ModelService;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@RestController
@RequestMapping("/regulationmanager")
public class RegulationControl {
	
	@Autowired
	private RegulationService service;

	@Autowired
	private ModelService modelService;
	
	/**
	 * 获得模型列表
	 * @param pageParam
	 * @return
	 */
	@RequestMapping("models")
	public ProTablePage<Model> getModels(ProTablePage<Model> pageParam,Model modelParams){
		return modelService.getModels(pageParam,modelParams);
	}
	
	
	/**
	 * 获取规则列表
	 * @param pageParam
	 * @param regulationParam
	 * @return
	 */
	@RequestMapping("regulations")
	public ProTablePage<Regulation> getRegulations(ProTablePage<Regulation> pageParam,Regulation regulationParam ){
		return service.getRegulations(pageParam, regulationParam);
	}
	
	/**
	 * 添加规则指标
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="addregulation",method=RequestMethod.POST)
	public String addRegulation(@RequestBody Regulation regulationParam) {
		if(regulationParam.getModelId()!=null&&regulationParam.getRegulationList().size()>0) {
			for(Regulation param :regulationParam.getRegulationList()){
				param.setModelId(regulationParam.getModelId());
				service.addRegulation(param);
			}
		}
		//request.getParameterNames(null).getAttributeNames().getAttribute(null).getParameter(null).getParameterMap().get("detailList")
		//request.getp
		return null;//service.addRegulation(definition);
	}
	
	/**
	 * 更新规则指标
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="updateregulation",method=RequestMethod.POST)
	public String updateRegulation(@RequestBody Regulation regulationParam) {
		return service.updateRegulation(regulationParam);
	}
	
	/**
	 * 更新规则指标
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="saveregulation",method=RequestMethod.POST)
	public String saveRegulation(@RequestBody Regulation regulationParam) {
		return service.saveRegulation(regulationParam);
	}
	
	/**
	 * 更新规则指标
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="delregulation",method=RequestMethod.POST)
	public String delRegulation(@RequestBody Regulation regulationParam) {
		return service.delRegulation(regulationParam);
	}
	
	
	@RequestMapping("semanticsindicators")
	public List<TreeNode> getSemanticsIndicators(TreeNode nodeParam){
		return service.getSemanticsIndicators(nodeParam);
	}
	
	/**
	 * 保存规则与指标的关联
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="addregulationindicator",method=RequestMethod.POST)
	public void addRegulationIndicator(@RequestBody Map<String,String> m ) {
		service.addRegulationIndicator( m.get("regulationId"), m.get("indicatorId"));
	}
	
	/**
	 * 删除规则与指标的关联
	 * @param request
	 * @param definition
	 * @return
	 */
	@RequestMapping(path="delregulationindicator",method=RequestMethod.POST)
	public void delRegulationIndicator(@RequestBody Map<String,String> m) {
		service.delRegulationIndicator( m.get("regulationId"), m.get("indicatorId"));
	}

	/**
	 * 获取规则与指标的关联
	 * @param regulationId
	 * @return
	 */
	@RequestMapping("getregulationindicator")
	public List<String> getRegulationIndicator(String regulationId){
		return service.getRegulationIndicator(regulationId);
	}
}
