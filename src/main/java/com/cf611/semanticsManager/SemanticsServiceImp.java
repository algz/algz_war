package com.cf611.semanticsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf611.util.ProTablePage;

@Service
@Transactional(readOnly = true)
public class SemanticsServiceImp implements SemanticsService {

	@Autowired
	private SemanticsRepository repository;
	
	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	@Override
	public ProTablePage<Semantics> getSemanticss(ProTablePage<Semantics> pageParam, Semantics semanticsParam) {
		Pageable pageable = PageRequest.of(pageParam.getCurrent() -1, pageParam.getPageSize());
		Page<Semantics> page= repository.findAll(Example.of(semanticsParam),pageable); //没有数据时，返回空列表
		pageParam.setPage(page);
		return pageParam;
	}

}
