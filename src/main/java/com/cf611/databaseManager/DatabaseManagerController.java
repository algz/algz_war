package com.cf611.databaseManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.util.TreeNode;

@RestController
@RequestMapping("/databasemanager")
public class DatabaseManagerController {

	@Autowired
	private DatabaseManagerService service;
	
	@RequestMapping("/findallmodestreenode")
	public List<TreeNode> findAllModesTreeNode(TreeNode nodeParam){
		return service.findAllModesTreeNode(nodeParam);
	}
}
