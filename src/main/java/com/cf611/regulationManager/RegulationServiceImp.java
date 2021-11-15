package com.cf611.regulationManager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.util.ProTablePage;

@Service
@Transactional(readOnly = true)
public class RegulationServiceImp implements RegulationService {

	@Autowired
	private RegulationRepository repository;
	
	/**
	 * 获取规则列表
	 * @param pageParam
	 * @param regulationParam
	 * @return
	 */
	@Override
	public ProTablePage<Regulation> getRegulations(ProTablePage<Regulation> pageParam,Regulation regulationParam){
		try {
			Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
			Page<Regulation>page =  SpringBeanUtils.PageListMapToPageEntity(repository.getRegulations(regulationParam.getModelId(),pageable), pageable, Regulation.class);
			pageParam.setPage(page);
			return pageParam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public String addRegulation(Regulation regulation) {
		repository.save(regulation);
		return "";
	}
	
	@Transactional
	@Override
	public String updateRegulation(Regulation regulation) {
		Regulation reg=repository.findById(regulation.getId()).get();
		reg.setRegulationVal(regulation.getRegulationVal());
		repository.save(reg);
		return null;
	}

	@Transactional
	@Override
	public String delRegulation(Regulation regulationParam) {
		repository.deleteById(regulationParam.getId());
		return null;
	}
}
