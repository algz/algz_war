package com.cf611.semanticsManager;

import java.util.List;

import org.apache.cxf.common.util.StringUtils;
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
		Sort sort=Sort.by(Sort.Direction.ASC, "createDate");
		Pageable pageable = PageRequest.of(pageParam.getCurrent() -1, pageParam.getPageSize(),sort);
		Page<Semantics> page= repository.findAll(Example.of(semanticsParam),pageable); //没有数据时，返回空列表
		pageParam.setPage(page);
		return pageParam;
	}

	/**
	 * 保存语义
	 * @param semantics
	 * @return
	 */
	@Override
	@Transactional
	public String saveSemantics(Semantics semantics) {
		repository.save(semantics); //save() 先查询ID是否存在，不存在则创建，存在则更新。
		return  null;
	}
	
	/**
	 * 删除语义
	 * @param semantics
	 * @return
	 */
	@Override
	@Transactional
	public String delSemantics(Semantics semantics) {
		if(!StringUtils.isEmpty(semantics.getId())) {
			repository.deleteById(semantics.getId());
		}
		return null;
	}
	
	/**
	 * 获取语义列表
	 * @param pageParam
	 * @param semanticsParam
	 * @return
	 */
	@Override
	public List<Semantics> getSemanticsList(Semantics semanticsParam) {
//		Sort sort=Sort.by(Sort.Direction.ASC, "createDate");
		//Pageable pageable = PageRequest.of(pageParam.getCurrent() -1, pageParam.getPageSize(),sort);
		List<Semantics> list= repository.findAll(Example.of(semanticsParam)); //没有数据时，返回空列表
		return list;
	}

}
