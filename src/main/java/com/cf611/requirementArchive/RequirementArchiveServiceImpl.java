package com.cf611.requirementArchive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.common.file.pathencode.APathCodeService;
import com.algz.platform.utility.ADateUtils;
import com.algz.platform.utility.SpringBeanUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.requirmentDataBase.modelBase.Model;
import com.cf611.util.ProTablePage;

@Service
public class RequirementArchiveServiceImpl implements RequirementArchiveService{

	@Autowired
	private RequirementDefinitionService definitionService;
	
	@Autowired
	private APathCodeService apathcodeService;
	
	@Override
	public ProTablePage<DefinitionView> getArchives(ProTablePage<DefinitionView> pageParam,
			DefinitionView definitionParam) {
		return definitionService.getDefinitionView(pageParam, definitionParam);
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
	public String saveArchive(MultipartFile[] files,DefinitionView view) {
		Definition def = definitionService.getDefinition(view.getId());
		def.setArchiveDate(ADateUtils.getCurrentDateTime());
		def.setState("5");
		definitionService.saveDefinition(def);
		
		//保存文件
		if (files!=null&&files.length > 0) {
			APathCode pathcode = new APathCode();
			pathcode.setRelationId(view.getId());
			pathcode.setRelationKind("CF_DEFINITION_REGULATION_VIEW");
			pathcode.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
			pathcode.setFileName(files[0].getOriginalFilename());
			apathcodeService.addAPathCode(files, pathcode);
			def.setArchiveFileId(pathcode.getId());
//			def.setArchiveFileName(pathcode.getFileName());
		}
		return null;
	}
}
