package com.algz.platform.common.file.pathencode;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface APathCodeService {

	/**
	 * 保存
	 * @param file
	 * @param pathCode
	 * @return
	 */
	String addAPathCode(MultipartFile file, APathCode pathCode);

	/**
	 * 保存
	 * @param files
	 * @param relationId
	 * @param relationKind
	 * @param remark
	 * @param creator
	 * @return
	 */
	List<String> addAPathCode(MultipartFile[] files, String relationId, String relationKind, String remark,
			String creator, String custmoDir,String fileName);

	/**
	 * 保存
	 * @param file
	 * @param pathCode
	 * @return
	 */
	List<String> addAPathCode(MultipartFile[] files, APathCode pathCode);

	/**
	 * 保存
	 * @param file
	 * @param pathCode
	 * @return
	 */
	String addAPathCode(MultipartFile file, APathCode pathCode, String custmoDir);

	/**
	 * 作为临时文件保存
	 * @param file
	 * @param pathCode
	 * @return
	 */
	String addAPathCode(MultipartFile file);

	/**
	 * 保存
	 * 路径："/"+RelationKind+custmoDir+ file.getOriginalFilename()
	 * @param file
	 * @param pathCode
	 * @return
	 */
	List<String> addAPathCode(MultipartFile[] files, APathCode pathCode, String custmoDir);

	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	String delAPathCode(String id);

	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	String delAPathCode(APathCode apathcode);

	/**
	 * 删除。
	 * @param id
	 * @return
	 */
	String delAPathCodeByRelationId(String relationId);


}
