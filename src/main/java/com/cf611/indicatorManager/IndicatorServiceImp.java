package com.cf611.indicatorManager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<TreeNode> GetIndicatorNodes(TreeNode nodeParam) {
		TreeNode node=new TreeNode(nodeParam.getKey());
		RecursionTreeNode(node);
		return node.getChildren();
	}

	private void RecursionTreeNode(TreeNode root){
		Indicator indicatorParam=new Indicator();
		indicatorParam.setParentId(root.getKey());
		List<Indicator> indicatorList=repository.findAll(Example.of(indicatorParam));
		if(indicatorList.size()!=0) {
			root.setChildren(new ArrayList<TreeNode>());
			for(Indicator it:indicatorList) {
				TreeNode node=new TreeNode(it.getId(),it.getName());
				root.getChildren().add(node);
				RecursionTreeNode(node);
			}
		}
	}
}
