package com.cf611.semanticsManager.semanticsKind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.demo.typicalExample.TypicalExample;
import com.cf611.indicatorManager.Indicator;
import com.cf611.semanticsManager.Semantics;
import com.cf611.semanticsManager.SemanticsRepository;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@Transactional(readOnly = true)
@Service
public class SemanticsKindServiceImp implements SemanticsKindService{

	@Autowired
	private SemanticsKindRepository repository;
	
	@Autowired
	private SemanticsRepository semanticsreRository;
	
	@Override
	public List<TreeNode> getSemanticsKinds(TreeNode nodeParam){
		List<SemanticsKind> kindList=repository.findAll();
		TreeNode node=new TreeNode();
		node.setKey("0");
		ToTreeNode(node,kindList);
		return node.getChildren();
	}

	/**
	 * 递归子结点
	 * 
	 * @param root
	 */
	private void  ToTreeNode(TreeNode root,List<SemanticsKind> teList) {
		if (teList.size() != 0) {
			root.setIsLeaf(false);
			root.setChildren(new ArrayList<TreeNode>());
			for (SemanticsKind it : teList) {
				TreeNode node = new TreeNode(it.getId(), it.getName());
//				Map<String,String> m=new HashMap<String,String>();
//				m.put("description", it.getDescription());
//				m.put("parentId", it.getParentId());
//				node.setExtProps(m); //自定义属性
				root.getChildren().add(node);
				//RecursionTreeNode(node,teList);
			}
		} else {
			root.setIsLeaf(true);
		}
	}

	@Transactional
	@Override
	public String saveSemanticsKind(SemanticsKind kind) {
		repository.save(kind);
		return null;
	}

	@Transactional
	@Override
	public String delSemanticsKind(SemanticsKind kind) {
		repository.deleteById(kind.getId());
		return null;
	}
	
	
	@Override
	public List<TreeNode> getSemanticsKindTree(){
		List<SemanticsKind> kindList=repository.findAll();
		TreeNode root=new TreeNode();
		root.setKey("0");
		root.setChildren(new ArrayList<TreeNode>());
		for(SemanticsKind sk : kindList) {
			TreeNode pnode = new TreeNode("p"+sk.getId(), sk.getName());
			pnode.setChildren(new ArrayList<TreeNode>());
			root.getChildren().add(pnode);
			Semantics semanticsParam=new Semantics();
			semanticsParam.setKindId(sk.getId());
			List<Semantics> semanticsList=semanticsreRository.findAll(Example.of(semanticsParam),Sort.by("createDate"));
			for(Semantics it:semanticsList) {
				TreeNode cnode=new TreeNode(it.getId(),it.getName());
				cnode.setIsLeaf(true);
				pnode.getChildren().add(cnode);
			}
		}		
		return root.getChildren();
	}


}
