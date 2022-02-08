package com.cf611.requirmentDataBase.semanticsBase;

import java.util.List;

import com.cf611.util.ProTablePage;

public interface SemanticsService {

	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	ProTablePage<Semantics> getSemanticss(ProTablePage<Semantics> pageParam, Semantics semanticsParam);

	/**
	 * 保存语义
	 * @param semantics
	 * @return
	 */
	String saveSemantics(Semantics semantics);

	/**
	 * 删除语义
	 * @param semantics
	 * @return
	 */
	String delSemantics(Semantics semantics);

	/**
	 * 
	 * @param semanticsParam
	 * @return
	 */
	List<Semantics> getSemanticsList(Semantics semanticsParam);
}
