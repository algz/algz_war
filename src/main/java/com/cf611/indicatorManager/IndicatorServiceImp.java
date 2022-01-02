package com.cf611.indicatorManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringBeanUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@Service
@Transactional(readOnly = true) 
public class IndicatorServiceImp implements IndicatorService {

	@Autowired
	private IndicatorRepository repository;
	

	@Override
	public ProTablePage<Indicator> GetIndicators(ProTablePage<Indicator> pageParam, Indicator indicatorParam) {
		//参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size 设置每页数据的条数
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		Page<Indicator> page= repository.findAll(pageable);
		pageParam.setPage(page);
		return pageParam;
	}

	@Override
	public Indicator GetIndicator(Indicator indicatorParam) {
		return repository.findById(indicatorParam.getId()).get();
	}

	@Override
	public List<TreeNode> GetIndicatorNodes(TreeNode nodeParam) {
		TreeNode node=new TreeNode(nodeParam.getKey());
		RecursionTreeNode(node);
		return node.getChildren();
	}

	private void RecursionTreeNode(TreeNode root){
		Indicator indicatorParam=new Indicator();
		indicatorParam.setSemanticsId(root.getKey());
		List<Indicator> indicatorList=repository.findAll(Example.of(indicatorParam));
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
				RecursionTreeNode(node);
			}
		}else {
			root.setIsLeaf(true);
		}
	}

	@Transactional
	@Override
	public String saveIndicator(Indicator indicatorParam) {
		indicatorParam.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		if(!StringUtils.isEmpty(indicatorParam.getId())) {
			Indicator ind=repository.findById(indicatorParam.getId()).get();
			SpringBeanUtils.copyPropertiesForbidNull(indicatorParam, ind);
			repository.save(ind);
		}else {
			repository.save(indicatorParam);
		}
		
		return null;
	}

	@Transactional
	@Override
	public String delIndicator(Indicator indicatorParam) {
		repository.deleteById(indicatorParam.getId());
		return null;
	}

	@Override
	public List<Indicator> getIndicatorsList(Indicator indicatorParam) {
		List<Indicator> list= repository.findAll(Example.of(indicatorParam));
		return list;
	}



}
