package com.cf611.modelManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf611.util.ProTablePage;

@Transactional(readOnly = true)
@Service
public class ModelServiceImp implements ModelService{

	@Autowired
	private ModelRepository repository;
	
	/**
	 * 获取模型列表
	 * @param pageParam
	 * @return
	 */
	@Override
	public ProTablePage<Model> getModels(ProTablePage<Model> pageParam){
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		pageParam.setPage(repository.findAll(pageable));
		return pageParam;
	}
}
