package com.cf611.requirmentDataBase.modelBase;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface ModelService {

	/**
	 * 获取模型列表
	 * @param pageParam
	 * @return
	 */
	ProTablePage<Model> getModels(ProTablePage<Model> pageParam,Model modelParams);

	/**
	 * 保存模型
	 * @param files
	 * @param model
	 * @return
	 */
	String saveModel(MultipartFile[] files,MultipartFile picFile, Model model);

	/**
	 * 获取模型
	 * @param model
	 * @return
	 */
	Model getModel(Model model);
	
	String delModel(Model model);

	/**
	 * 删除模型文件。
	 * @param id
	 * @param filePath
	 * @return
	 */
	String delModelFile(String id, String filePath);

	List<TreeNode> getModelNodes(TreeNode nodeParam);
}
