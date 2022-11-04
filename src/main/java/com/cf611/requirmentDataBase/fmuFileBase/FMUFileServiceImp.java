package com.cf611.requirmentDataBase.fmuFileBase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cf611.util.ProTablePage;

@Service
public class FMUFileServiceImp implements FMUFileService{
	
	@Autowired
	private FMUFileRespository repository;

	@Override
	public ProTablePage<FMUFile> getFMUFiles(ProTablePage<FMUFile> pageParam,FMUFile param) {
		Pageable pageable=null;
		if(!StringUtils.isEmpty(pageParam.getCurrent()+"")) {
			pageable= PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		}
		
		Page<FMUFile> page= repository.findAll(Example.of(param),pageable);
		pageParam.setPage(page);
		return pageParam;

	}
}