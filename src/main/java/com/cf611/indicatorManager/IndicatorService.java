package com.cf611.indicatorManager;

import java.util.List;

import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface IndicatorService {
	
	public ProTablePage<Indicator> GetIndicators(ProTablePage<Indicator> pageParam,Indicator indicatorParam);
	
	public List<TreeNode> GetIndicatorNodes(TreeNode nodeParam);
}