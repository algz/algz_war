package com.cf611.requirmentJudge;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.algz.platform.utility.ADateUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definition.DefinitionRepository;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.requirementDefinition.definitionView.DefinitionViewRepository;
import com.cf611.util.ProTablePage;

@Service
public class RequirementJudgeServiceImpl implements RequirementJudgeService {

	@Autowired
	private RequirementDefinitionService definitionService;
	
	@Autowired
	private DefinitionRepository definitionRepository;
	
	@Override
	public ProTablePage<DefinitionView> GetJudges(ProTablePage<DefinitionView> pageParam,
			DefinitionView definitionParam) {
		return definitionService.getDefinitionView(pageParam, definitionParam);
	}

	/**
	 * 接收需求
	 */
	@Transactional
	@Override
	public String receiveDefinition(Definition params) {
		
		//获得持化久对象，不用save
		Definition def=definitionRepository.findOne(Example.of(params)).get();
		def.setReceiver(SpringSecurityUtils.getCurrentUser().getUserid());
		def.setReceiveDate(ADateUtils.getCurrentDateTime());
		def.setState("4");
//		definitionRepository.save(def);
		
		return "";
	}
}
