package com.cf611.regulationManager;

import com.cf611.util.ProTablePage;

public interface RegulationService {

	/**
	 * 获取规则列表
	 * @param pageParam
	 * @param regulationParam
	 * @return
	 */
	ProTablePage<Regulation> getRegulations(ProTablePage<Regulation> pageParam, Regulation regulationParam);

	String addRegulation(Regulation definition);

	String updateRegulation(Regulation regulationParam);

	String delRegulation(Regulation regulationParam);

}
