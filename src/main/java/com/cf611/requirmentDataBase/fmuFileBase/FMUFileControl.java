package com.cf611.requirmentDataBase.fmuFileBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.util.ProTablePage;

@RequestMapping("/fmufilemanager")
@RestController
public class FMUFileControl {

	@Autowired
	private FMUFileService service;
	
	/**
	 * 获取需求详情
	 * @param pageParam
	 * @param indicatorParam
	 * @return
	 */
	@RequestMapping("/fmufiles")
	public ProTablePage<FMUFile> getFMUFiles(ProTablePage<FMUFile> pageParam,FMUFile param) {
		return service.getFMUFiles(pageParam, param);
	}
}
