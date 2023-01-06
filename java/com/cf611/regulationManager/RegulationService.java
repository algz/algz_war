package com.cf611.regulationManager;

import java.util.List;

import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface RegulationService {

	/**
	 * 获取规则列表
	 * @param pageParam
	 * @param regulationParam
	 * @return
	 */
	ProTablePage<Regulation> getRegulations(ProTablePage<Regulation> pageParam, Regulation regulationParam);

	String saveRegulation(Regulation reg);
	
	String addRegulation(Regulation definition);

	String updateRegulation(Regulation regulationParam);

	String delRegulation(Regulation regulationParam);
	
	List<TreeNode> getSemanticsIndicators(TreeNode nodeParam);
	
	void addRegulationIndicator(String regulationId,String indicatorId);
	
	void delRegulationIndicator(String regulationId,String indicatorId);

	List<String> getRegulationIndicator(String regulationId);
}
