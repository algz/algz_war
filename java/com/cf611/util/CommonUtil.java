/**
 * 
 */
package com.cf611.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;

import com.cf611.indicatorManager.Indicator;

/**
 * @author algz
 *
 */
public final class CommonUtil {
//
//	public static <T> void RecursionTreeNode(TreeNode root,List<T> arr,Class<T> t){
////		Indicator indicatorParam=new Indicator();
//		T indicatorParam=t.newInstance();
//		indicatorParam.setParentId(root.getKey());
//		List<Indicator> indicatorList=repository.findAll(Example.of(indicatorParam));
//		if(indicatorList.size()!=0) {
//			root.setIsLeaf(false);
//			root.setChildren(new ArrayList<TreeNode>());
//			for(Indicator it:indicatorList) {
//				TreeNode node=new TreeNode(it.getId(),it.getName());
//				Map<String,String> m=new HashMap<String,String>();
//				m.put("description", it.getDescription());
//				m.put("parentId", it.getParentId());
//				node.setExtProps(m);
//				root.getChildren().add(node);
//				RecursionTreeNode(node,arr);
//			}
//		}else {
//			root.setIsLeaf(true);
//		}
//	}
}
