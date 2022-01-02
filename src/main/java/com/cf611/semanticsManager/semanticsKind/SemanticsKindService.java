package com.cf611.semanticsManager.semanticsKind;

import java.util.List;

import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface SemanticsKindService {

	List<TreeNode> getSemanticsKinds(TreeNode nodeParam);
	
	String saveSemanticsKind(SemanticsKind kind);

	String delSemanticsKind(SemanticsKind kind);
	
	/**
	 * 获取语义库树
	 * @return
	 */
	List<TreeNode> getSemanticsKindTree();
}
