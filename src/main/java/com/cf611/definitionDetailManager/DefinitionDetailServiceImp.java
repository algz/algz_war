package com.cf611.definitionDetailManager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailView;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailViewRepository;
import com.cf611.util.ProTablePage;


@Service
@Transactional(readOnly = true) 
public class DefinitionDetailServiceImp implements DefinitionDetailService{

	@Autowired
	private DefinitionDetailRepository repository;
	
	@Autowired
	private DefinitionDetailViewRepository definitionDetailViewRepository;
	
	/**
	 * 添加需求详情
	 */
	@Override
	public String AddDefinitionDetail(DefinitionDetail detail) {
		repository.save(detail);
		return null;
	}

	/**
	 * 获取需求详情
	 */
	@Override
	public ProTablePage<DefinitionDetail> GetDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,
			DefinitionDetail definitionDetailParam) {
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		Page<DefinitionDetail> page= repository.findAll(Example.of(definitionDetailParam),pageable);
		pageParam.setPage(page);
		return pageParam;

	}

	/**
	 * 通过 definitonId ，从 CF_DEFINITIONDETAIL_VIEW 获取需求详情列表
	 */
	@Override
	public List<DefinitionDetailView> getDefinitionDetailByDefinitionId(String definitonId) {
		DefinitionDetailView ddv=new DefinitionDetailView();
		ddv.setDefinitionId(definitonId);
		return definitionDetailViewRepository.findAll(Example.of(ddv));
//		List<Map<String,Object>> mapList=repository.getDefinitionDetail(definitonId);
//		try {
//			return SpringBeanUtils.ListMapToListEntity(mapList, DefinitionDetail.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	/**
	 * 更新需求详情列表
	 */
	@Transactional
	@Override
	public String updateDefinitionDetail(DefinitionDetail param) {
		DefinitionDetail detail=repository.findById(param.getId()).get();
		SpringBeanUtils.copyPropertiesForbidNull(param, detail);
		repository.save(detail);
		return detail.getId();
	}
	
	
}
