package com.cf611.requirmentDataBase.indicatorBase;

import java.util.List;

import com.cf611.indicatorManager.Indicator;
import com.cf611.semanticsManager.Semantics;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface IndicatorBaseService {

	public TreeNode getIndicatorTreeNodes();
	
	public ProTablePage<Indicator> getIndicatorTable(ProTablePage<Indicator> pageParam,Indicator indicatorParam);
	
	public ProTablePage<Semantics> getSemanticsTable(ProTablePage<Semantics> pageParam,Semantics param);


	List<String> getSemanticsByIndicatorId(String indicatorId);

	String delIndicatorSemantics(String indicatorId, String semanticsId);

	String addIndicatorSemantics(String indicatorId, String semanticsId);

	/**
	 * 获取指标
	 * @param indicatorParam
	 * @return
	 */
	Indicator getIndicator(Indicator indicatorParam);

	String saveIndicator(Indicator indicatorParam);

	String delIndicator(Indicator indicatorParam);
}
