package com.cf611.databaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algz.database.DatabaseService;
import com.cf611.util.TreeNode;

@Service
public class DatabaseManagerServiceImpl implements DatabaseManagerService {

	@Autowired
	private DatabaseService databaseService;
	
	@Override
	public List<TreeNode> findAllModesTreeNode(TreeNode nodeParam) {
		List<TreeNode> nodeList=new ArrayList<TreeNode>();
		if(nodeParam.getKey()==null) {
			//查询所有的模式
			List<Map<String,Object>> list=databaseService.findAllUsers();
			for(Map<String,Object> m : list) {
				TreeNode n=new TreeNode("mode_"+m.get("user_id"),m.get("username")+"");
				
				n.setChildren(new ArrayList<TreeNode>());
				TreeNode cn=new TreeNode("table_"+m.get("username"),"表");
				n.getChildren().add(cn);
				cn=new TreeNode("view_"+m.get("username"),"视图");
				n.getChildren().add(cn);
				nodeList.add(n);
			}
		}else {
			String key=nodeParam.getKey();
			String title=nodeParam.getTitle();
			String kind=nodeParam.getKey().split("_")[0];
			String modeName=nodeParam.getKey().split("_")[1];
			switch(title) {
				case "表":
					List<Map<String,Object>> tableList=databaseService.findAllTables(modeName);
					for(Map<String,Object> m : tableList) {
						TreeNode n=new TreeNode("table_"+modeName+"."+m.get("table_name")+"",m.get("table_name")+"");
						n.setIsLeaf(true);
						nodeList.add(n);
					}
					break;
				case "视图":
					List<Map<String,Object>> viewList=databaseService.findAllViews(modeName);
					for(Map<String,Object> m : viewList) {
						TreeNode n=new TreeNode("view_"+modeName+"."+m.get("view_name")+"",m.get("view_name")+"");
						n.setIsLeaf(true);
						nodeList.add(n);
						
					}
					break;
			}
		}
		
		return nodeList;
	}


	


}
