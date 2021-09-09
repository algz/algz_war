package com.cf611.modelManager;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algz.platform.common.file.FileUtil;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/modelmanager")
public class ModelControl {
	
	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;
	
	@Autowired
	private ModelService service;
	
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
	 * picFile 必须设置为数组，不然没有文件上传时，会报异常“Required request part 'picFile' is not present]”。
	 * @param files
	 * @param extra
	 * @return
	 */
	@PostMapping("/savemodel")
	public String saveModel(@RequestParam("file") MultipartFile[] files,@RequestParam("picFile") MultipartFile[] picFile, Model model) {
		return service.saveModel(files,picFile.length==0?null:picFile[0], model);
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
}
