package com.cf611.modelManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.algz.demo.typicalExample.TypicalExample;
import com.algz.platform.common.file.FileUtil;
import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.common.file.pathencode.APathCodeRepository;
import com.algz.platform.common.file.pathencode.APathCodeService;
import com.algz.platform.utility.SpringBeanUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.semanticsManager.Semantics;
import com.cf611.semanticsManager.semanticsKind.SemanticsKind;
import com.cf611.semanticsManager.semanticsKind.SemanticsKindRepository;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@Transactional(readOnly = true)
@Service
public class ModelServiceImp implements ModelService {

	@Autowired
	private ModelRepository repository;

	@Autowired
	private SemanticsKindRepository semanticsKindRepository;
	
	@Autowired
	private APathCodeService apathcodeService;

	/**
	 * 获取模型列表
	 * 
	 * @param pageParam
	 * @return
	 */
	@Override
	public ProTablePage<Model> getModels(ProTablePage<Model> pageParam,Model modelParams) {
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		pageParam.setPage(repository.findAll(Example.of(modelParams),pageable));
		return pageParam;
	}

	/**
	 * 获取模型
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public Model getModel(Model model) {
		Map<String, Object> map = repository.getModelForMapList(model.getId());
		try {
			return SpringBeanUtils.MapToEntity(map, Model.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存模型
	 * 
	 * @param files
	 * @param model
	 * @return
	 */
	@Override
	@Transactional
	public String saveModel(MultipartFile[] files,MultipartFile picFile, Model model) {

		List<String> filePathList = new ArrayList<String>();
		List<String> fileNameList = new ArrayList<String>();
		if (StringUtils.isEmpty(model.getId())){
			//新增
			model.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		}else {
			//编辑
			Model tmodel = repository.findById(model.getId()).get();
			if(tmodel.getFilePath()!=null) {
				filePathList.addAll(Arrays.asList(tmodel.getFilePath().split(",")));
				fileNameList.addAll(Arrays.asList(tmodel.getFileName().split(",")));
			}
			SpringBeanUtils.copyPropertiesForbidNull(model, tmodel);
			model = tmodel;
		}
		repository.save(model);
		
		//保存图片
		if(picFile!=null) {
			apathcodeService.delAPathCode(model.getPicPath());
			
			APathCode pathcode = new APathCode();
			pathcode.setFilePath(picFile.getOriginalFilename());
			pathcode.setRelationId(model.getId());
			pathcode.setRelationKind("model");
			pathcode.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
			pathcode.setFileName(picFile.getOriginalFilename());

			model.setPicPath(apathcodeService.addAPathCode(picFile, pathcode, model.getName()));
		}
		//保存文件
		if (files.length > 0) {
			for (MultipartFile file : files) {
				APathCode pathcode = new APathCode();
				pathcode.setRelationId(model.getId());
				pathcode.setRelationKind("model");
				pathcode.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
				pathcode.setFileName(file.getOriginalFilename());
				filePathList.add(apathcodeService.addAPathCode(file, pathcode, model.getName()));
				fileNameList.add(file.getOriginalFilename());
			}
			model.setFilePath(String.join(",", filePathList));
			model.setFileName(String.join(",", fileNameList));
		}
		return null;
	}

	@Transactional
	@Override
	public String delModel(Model model) {
		repository.deleteById(model.getId());
		apathcodeService.delAPathCodeByRelationId(model.getId());
		return "";
	}
	
	/**
	 * 删除模型文件。
	 * @param id
	 * @param filePath
	 * @return
	 */
	@Override
	@Transactional
	public String delModelFile(String id,String filePath) {
		Model m=repository.findById(id).get();
		String[] filePaths=m.getFilePath().split(",");
		String[] fileNames=m.getFileName().split(",");
		//Arrays.asList 获得的List不是真正的List（不能add,remove，否则报错），所以必须new ArrayList。
		List<String> filePathList=new ArrayList<String>(Arrays.asList(filePaths));
		List<String> fileNameList=new ArrayList<String>(Arrays.asList(fileNames));
		int i=filePathList.indexOf(filePath);
		filePathList.remove(i);
		fileNameList.remove(i);
		m.setFilePath(String.join(",", filePathList));
		m.setFileName(String.join(",", fileNameList));
		repository.save(m);
		
		apathcodeService.delAPathCode(filePath);
		return null;
	}

	@Override
	public List<TreeNode> getModelNodes(TreeNode nodeParam) {
		List<SemanticsKind> kindList=semanticsKindRepository.findAll();
		TreeNode root=new TreeNode();
		root.setKey("0");
		root.setChildren(new ArrayList<TreeNode>());
		for(SemanticsKind sk : kindList) {
			TreeNode pnode = new TreeNode("p"+sk.getId(), sk.getName());
			pnode.setChildren(new ArrayList<TreeNode>());
			root.getChildren().add(pnode);
			Model semanticsParam=new Model();
			semanticsParam.setKindId(sk.getId());
			List<Model> modelsList=repository.findAll(Example.of(semanticsParam),Sort.by("createDate"));
			for(Model it:modelsList) {
				TreeNode cnode=new TreeNode(it.getId(),it.getName()+"("+it.getSubmodelName()+")");
				cnode.setIsLeaf(true);
				Map<String,String> m=new HashMap<String,String>();
				m.put("parentId", pnode.getKey());
				cnode.setExtProps(m);
				pnode.getChildren().add(cnode);
			}
		}		
		return root.getChildren();
	}
}
