package com.cf611.semanticsManager;

import com.cf611.util.ProTablePage;

public interface SemanticsService {

	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	ProTablePage<Semantics> getSemanticss(ProTablePage<Semantics> pageParam, Semantics semanticsParam);


}
