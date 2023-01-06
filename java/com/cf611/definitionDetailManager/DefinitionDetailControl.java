package com.cf611.definitionDetailManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.indicatorManager.Indicator;
import com.cf611.util.ProTablePage;


@RequestMapping("/definitiondetailmanager")
@RestController
public class DefinitionDetailControl {
	
	@Autowired
	private DefinitionDetailService service;
	
	/**
	 * 添加需求详情
	 * @param detailParam
	 * @return
	 */
	@RequestMapping(path = "/adddefinitiondetail",method=RequestMethod.POST)
	public String addDefinitionDetail(DefinitionDetail detailParam) {
		return service.AddDefinitionDetail(detailParam);
	}
	
	/**
	 * 获取需求详情
	 * @param pageParam
	 * @param indicatorParam
	 * @return
	 */
	@RequestMapping("/definitiondetails")
	public ProTablePage<DefinitionDetail> getDefinitiionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetailParam) {
		return service.GetDefinitionDetails(pageParam, definitionDetailParam);
	}
	

}
