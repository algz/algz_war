package com.cf611.requirementArchive;

import org.springframework.web.multipart.MultipartFile;

import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.util.ProTablePage;

public interface RequirementArchiveService {

	ProTablePage<DefinitionView> getArchives(ProTablePage<DefinitionView> pageParam,DefinitionView definitionParam);
	
	String saveArchive(MultipartFile[] files,DefinitionView view);
}
