package com.cf611.requirementArchive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.requirmentDataBase.modelBase.Model;
import com.cf611.requirmentJudge.RequirementJudgeService;
import com.cf611.util.ProTablePage;

/**
 * 需求存档
 * @author algz
 *
 */
@RestController
@RequestMapping("/requirmentarchive")
public class RequirementArchiveControl {

	@Autowired
	private RequirementArchiveService service;
	
	/**
	 * 获取需求填充列表
	 * @param pageParam
	 * @param definitionParam
	 * @return
	 */
	@RequestMapping("/archives")
	public ProTablePage<DefinitionView> getArchives(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam) {
		
		return service.getArchives(pageParam,definitionParam);
	}
	
	/**
	 * 保存模型。
	 * 必须设置required=false，不然没有文件上传时(参数里没有"file"字段)，会报异常“Required request part 'picFile' is not present]”。
	 * @param files
	 * @param extra
	 * @return
	 */
	@PostMapping("/savearchive")
	public String saveArchive(@RequestParam(value="file",required=false) MultipartFile[] files, DefinitionView view) {
		return service.saveArchive(files, view);
	}
	
	
//	@PostMapping("/savearchive")
//	public String saveArchive@RequestParam(value="file",required=false) MultipartFile[] files,@RequestParam(value="picFile",required=false) MultipartFile picFile, Model model) {
//		return service.saveModel(files,picFile, model);
//	}
}
