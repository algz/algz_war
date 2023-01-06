package com.cf611.requirmentDataBase.regulationBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.indicatorManager.Indicator;
import com.cf611.indicatorManager.IndicatorRepository;
import com.cf611.requirmentDataBase.semanticsBase.Semantics;
import com.cf611.requirmentDataBase.semanticsBase.SemanticsRepository;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;


@Service
public class RegulationBaseServiceImp implements RegulationBaseService{

	@Autowired
	private IndicatorRepository indicatorRepository;
	
	@Autowired
	private SemanticsRepository semanticsRepository;
	
	@Override
	public TreeNode getIndicatorTreeNodes() {
		TreeNode root=new TreeNode("0","全部");
		Indicator indicatorParam=new Indicator();
		indicatorParam.setSemanticsId(root.getKey());
		List<Indicator> indicatorList=indicatorRepository.findAll(Example.of(indicatorParam));
		if(indicatorList.size()!=0) {
			root.setIsLeaf(false);
			root.setChildren(new ArrayList<TreeNode>());
			for(Indicator it:indicatorList) {
				TreeNode node=new TreeNode(it.getId(),it.getName());
				Map<String,String> m=new HashMap<String,String>();
				m.put("description", it.getDescription());
				m.put("parentId", it.getSemanticsId());
				node.setExtProps(m);
				root.getChildren().add(node);
				//RecursionTreeNode(node);
			}
		}else {
			root.setIsLeaf(true);
		}
		return root;
	}

	@Override
	public ProTablePage<Indicator> getIndicatorTable(ProTablePage<Indicator> pageParam, Indicator indicatorParam) {
		Pageable pageable = PageRequest.of(0, 9999,Sort.by("createDate").descending());
		Page<Indicator> page= indicatorRepository.findAll(Example.of(indicatorParam),pageable);
		pageParam.setPage(page);
		return pageParam;
	}

	/**
	 * 获取指标
	 * @param indicatorParam
	 * @return
	 */
	@Override
	public Indicator getIndicator(Indicator indicatorParam) {
		if(StringUtils.isEmpty(indicatorParam.getId())) {
			return null;
		}else {
			return indicatorRepository.findById(indicatorParam.getId()).get();
		}
	}
	
	@Override
	public ProTablePage<Semantics> getSemanticsTable(ProTablePage<Semantics> pageParam, Semantics param) {
		Pageable pageable = PageRequest.of(0, 9999,Sort.by("Name").descending());
		Page<Semantics> page= semanticsRepository.findAll(Example.of(param),pageable);
		pageParam.setPage(page);
		return pageParam;
	}


	@Transactional
	@Override
	public String addIndicatorSemantics(String indicatorId,String semanticsId) {
		Long d=indicatorRepository.countIndicatorSemantics(indicatorId, semanticsId);
		if(d.longValue()==0) {
			String id=UUID.randomUUID().toString().replaceAll("-","");
			indicatorRepository.addIndicatorSemantics(id, indicatorId, semanticsId);
		}
		return null;
	}
	
	@Transactional
	@Override
	public String delIndicatorSemantics(String indicatorId,String semanticsId) {
		indicatorRepository.delIndicatorSemantics(indicatorId, semanticsId);
		return null;
	}
	

	@Transactional
	@Override
	public String saveIndicator(Indicator indicatorParam) {
		indicatorParam.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		indicatorRepository.save(indicatorParam);
		return null;
	}

	@Transactional
	@Override
	public String delIndicator(Indicator indicatorParam) {
		indicatorRepository.deleteById(indicatorParam.getId());
		return null;
	}

	
	@Override
	public List<String> getSemanticsByIndicatorId(String indicatorId) {
		List<Map<String,Object>> list=indicatorRepository.getIndicatorSemanticsForMapList(indicatorId);
		List<String> strList=new ArrayList<String>();
		for(Map<String,Object> map : list) {
			strList.add(map.get("SEMANTICSID")+"");
		}
		return strList;
	}
}
