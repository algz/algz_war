package com.cf611.indicatorManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@RequestMapping("/indicatormanager")
@RestController
public class IndicatorControl {

	@Autowired
	private IndicatorService service;
	
	@RequestMapping("/indicator")
	public Indicator getIndicator(Indicator indicatorParam) {
		return service.GetIndicator(indicatorParam);
	}
	
	@RequestMapping("/indicators")
	public ProTablePage<Indicator> getIndicators(ProTablePage<Indicator> pageParam,Indicator indicatorParam) {
		return service.GetIndicators(pageParam,indicatorParam);
	}
	
	@RequestMapping("/indicatornodes")
	public List<TreeNode> getIndicatorNodes(TreeNode nodeParam){
		return service.GetIndicatorNodes(nodeParam);
	}
	
	@RequestMapping(path="saveindicator",method = RequestMethod.POST)
	public String saveIndicator(Indicator indicatorParam) {
		return service.saveIndicator(indicatorParam);
	}
	
	@RequestMapping(path="delindicator",method=RequestMethod.POST)
	public String delIndicator(Indicator indicatorParam) {
		return service.delIndicator(indicatorParam);
	}
}
