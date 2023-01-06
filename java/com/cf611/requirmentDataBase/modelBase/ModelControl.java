package com.cf611.requirmentDataBase.modelBase;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algz.platform.common.file.FileUtil;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;


@RestController
@RequestMapping("/modelmanager")
public class ModelControl {
	
	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;
	
	@Autowired
	private ModelService service;
	
	//日志
	private  final Logger log=LoggerFactory.getLogger(ModelControl.class);
	
	/**
	 * 获取模型列表。
	 * @param pageParam
	 * @return
	 */
	@RequestMapping("models")
	public ProTablePage<Model> getModels(ProTablePage<Model> pageParam,Model modelParams) {
		return service.getModels(pageParam,modelParams);
	}
	
	/**
	 * 获取模型。
	 * @param pageParam
	 * @return
	 */
	@RequestMapping("model")
	public Model getModel(Model model) {
		return service.getModel(model);// service.getModels(pageParam);
	}
	
	/**
	 * 保存模型。
	 * 必须设置required=false，不然没有文件上传时(参数里没有"file"字段)，会报异常“Required request part 'picFile' is not present]”。
	 * @param files
	 * @param extra
	 * @return
	 */
	@PostMapping("/savemodel")
	public String saveModel(@RequestParam(value="file",required=false) MultipartFile[] files,@RequestParam(value="picFile",required=false) MultipartFile picFile, Model model) {
		return service.saveModel(files,picFile, model);
	}
	
	/**
	 * 删除模型组件。
	 * @param mode
	 * @return
	 */
	@RequestMapping("delmodel")
	public String delModel(Model mode) {
		return service.delModel(mode);
	}
	
	/**
	 * 删除模型文件。
	 * @param mode
	 * @return
	 */
	@RequestMapping("delmodelfile")
	public String delModelFile(Model model) {
		return service.delModelFile(model.getId(),model.getFilePath());
	}

	@RequestMapping("modelnodes")
	public List<TreeNode> getModelNodes(TreeNode nodeParam){
		log.info("正在调用 getModelNodes ");
		return service.getModelNodes(nodeParam);
	}
}
