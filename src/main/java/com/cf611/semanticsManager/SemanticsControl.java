package com.cf611.semanticsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/semanticsmanager")
public class SemanticsControl {

	@Autowired
	private SemanticsService service;
	
	@RequestMapping("semanticss")
	public ProTablePage<Semantics> getSemanticss(ProTablePage<Semantics> pageParam,Semantics semanticsParam){
		return service.getSemanticss(pageParam, semanticsParam);
	}
}
