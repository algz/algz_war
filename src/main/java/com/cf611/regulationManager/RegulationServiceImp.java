package com.cf611.regulationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return null;
	}
}
