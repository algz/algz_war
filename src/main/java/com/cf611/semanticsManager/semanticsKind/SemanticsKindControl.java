package com.cf611.semanticsManager.semanticsKind;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@RestController
@RequestMapping("/semanticskindmanager")
public class SemanticsKindControl {

	@Autowired
	private SemanticsKindService service;
	
	@RequestMapping("semanticskinds")
	public List<TreeNode> getSemanticsKinds(TreeNode nodeParam){
		return service.getSemanticsKinds(nodeParam);
	}
	
	@RequestMapping(path="savesemanticskind",method=RequestMethod.POST)
	public String saveSemanticsKind(SemanticsKind kind) {
		return service.saveSemanticsKind(kind);
	}
	
	@RequestMapping(path="delsemanticskind",method=RequestMethod.POST)
	public String delSemanticsKind(SemanticsKind kind) {
		return service.delSemanticsKind(kind);
	}
	
	/**
	 * 获取语义库树
	 * @return
	 */
	@RequestMapping("semanticskindtree")
	public List<TreeNode> getSemanticsKindTree(){
		return service.getSemanticsKindTree();
	}
}
