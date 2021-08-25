package com.cf611.modelManager;

import com.cf611.util.ProTablePage;

public interface ModelService {

	/**
	 * 获取模型列表
	 * @param pageParam
	 * @return
	 */
	ProTablePage<Model> getModels(ProTablePage<Model> pageParam);

}
