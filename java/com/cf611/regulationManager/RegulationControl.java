package com.cf611.regulationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.modelManager.Model;
import com.cf611.modelManager.ModelService;
import com.cf611.util.ProTablePage;

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
}
