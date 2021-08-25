package com.cf611.modelManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/modelmanager")
public class ModelControl {
	
	@Autowired
	private ModelService service;
	
	/**
	 * 获取模型列表。
	 * @param pageParam
	 * @return
	 */
	@RequestMapping("models")
	public ProTablePage<Model> getModels(ProTablePage<Model> pageParam) {
		return service.getModels(pageParam);
	}
	
}
