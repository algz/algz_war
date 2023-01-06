package com.algz.platform.common.file.pathencode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.algz.platform.common.file.FileUtil;



@Transactional(readOnly = true)
@Service
public class APathCodeServiceImp implements APathCodeService{

	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;
	
	@Autowired
	private APathCodeRepository repository;
	
	
	/**
	 * 保存
	 * 保存路径："/"+pathCode.getRelationKind()+"/"+ file.getOriginalFilename();
	 * @param file
	 * @param pathCode
	 * @return
	 */
	@Override
	public String addAPathCode(MultipartFile file,APathCode pathCode) {
		return addAPathCode(file,pathCode,"");
	}
	
	/**
	 * 作为临时文件保存
	 * 路径："/temporary/"+ file.getOriginalFilename();
	 * @param file
	 * @param pathCode
	 * @return
	 */
	@Override
	public String addAPathCode(MultipartFile file) {
		APathCode pathCode=new APathCode();
		pathCode.setRelationKind("temporary");//临时文件夹
		pathCode.setCreator("1");//管理员
		return addAPathCode(file,pathCode,"");
	}
	
	/**
	 * 保存文件，并且写入数据库。
	 * 路径："/"+RelationKind+custmoDir+ file.getOriginalFilename()
	 * @param file
	 * @param pathCode
	 * @return
	 */
	@Transactional
	@Override
	public String addAPathCode(MultipartFile file,APathCode pathCode,String custmoDir) {
		custmoDir=StringUtils.isEmpty(custmoDir)?"":(custmoDir+"/");
		String RelationKind=StringUtils.isEmpty(pathCode.getRelationKind())?"":(pathCode.getRelationKind()+"/");
		String path="/"+RelationKind+custmoDir+ file.getOriginalFilename();
		File dest=new File(filestorePath+path);
		path=FileUtil.addStorageServerFile(dest, file);
		pathCode.setFilePath(path.substring(filestorePath.length()));
		repository.save(pathCode);
		return pathCode.getId();
	}
	
	/**
	 * 保存
	 * 路径："/"+RelationKind+custmoDir+ file.getOriginalFilename()
	 * @param file
	 * @param pathCode
	 * @return
	 */
	@Transactional
	@Override
	public List<String> addAPathCode(MultipartFile[] files,APathCode pathCode,String custmoDir) {
		List<String> list=new ArrayList<String>();
		for(MultipartFile file : files) {
			list.add(addAPathCode(file, pathCode,custmoDir)) ;
		}
		return list;
	}
	
	/**
	 * 保存
	 * @param file
	 * @param pathCode
	 * @return
	 */
	@Override
	public List<String> addAPathCode(MultipartFile[] files,APathCode pathCode) {
		List<String> list=new ArrayList<String>();
		for(MultipartFile file : files) {
			list.add(addAPathCode(file, pathCode)) ;
		}
		return list;
	}
	

	/**
	 * 保存
	 * @param files
	 * @param relationId
	 * @param relationKind
	 * @param remark
	 * @param creator
	 * @return
	 */
	@Transactional
	@Override
	public List<String> addAPathCode(MultipartFile[] files,String relationId,String relationKind,String remark,String creator,String custmoDir,String fileName) {
		APathCode pathCode=new APathCode();
		pathCode.setRelationId(relationId);
		pathCode.setRelationKind(relationKind);
		pathCode.setRemark(remark);
		pathCode.setCreator(creator);
		pathCode.setFilePath(filestorePath);
		pathCode.setFileName(StringUtils.isEmpty(fileName)?new File(filestorePath).getName():fileName);
		List<String> list=new ArrayList<String>();
		for(MultipartFile file : files) {
			list.add(addAPathCode(file, pathCode,custmoDir)) ;
		}
		return list;
	}
	
//	/**
//	 * 仅保存数据，不保存文件。
//	 */
//	@Transactional
//	public String addAPathCode(APathCode pathcode) {
//		repository.save(pathcode);
//		return "";
//	}
	
	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	@Transactional
	@Override
	public String delAPathCode(String id) {
		if(!StringUtils.isEmpty(id)) {
			APathCode code=repository.findById(id).orElse(null);
			if(code!=null) {
				repository.delete(code);
				File file=new File(filestorePath+code.getFilePath());
				if(file.exists()) {
					file.delete();
				}
			}
			return "";
		}
		return "";
	}
	
	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	@Transactional
	@Override
	public String delAPathCodeByRelationId(String relationId) {
		APathCode apathcode=new APathCode();
		apathcode.setRelationId(relationId);
		List<APathCode> list=repository.findAll(Example.of(apathcode));
		

		for(APathCode code:list) {
			File file=new File(filestorePath+code.getFilePath());
			file=file.isFile()?file.getParentFile():file;
			if(file.exists()) {
				try {
					FileUtils.deleteDirectory(file);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
//			repository.delete(code);
		}
		repository.delAPathCodeByRelationId(relationId);
//		repository.delete(apathcode);
		return "";
	}
	
	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public String delAPathCode(APathCode apathcode) {
		List<APathCode> list=repository.findAll(Example.of(apathcode));
		repository.delete(apathcode);
		for(APathCode code:list) {
			File file=new File(filestorePath+code.getFilePath());
			if(file.exists()) {
				file.delete();
			}
		}
		return null;
	}
}
