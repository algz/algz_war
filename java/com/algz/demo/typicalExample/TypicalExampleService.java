package com.algz.demo.typicalExample;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

public interface TypicalExampleService {

	/**
	 * 分页查询
	 */
	Page<TypicalExample> getExamples(ProTablePage<TypicalExample> pageParam, TypicalExample exampleParam);

	/**
	 * List查询
	 */
	List<TypicalExample> getExamples(TypicalExample exampleParam);

	/**
	 * List复杂查询
	 */
	List<TypicalExample> getSpecificationOfExamples(TypicalExample exampleParam);

	/**
	 * 分页复杂查询
	 */
	ProTablePage<TypicalExample> getSpecificationOfExamples(ProTablePage<TypicalExample> pageParam, TypicalExample exampleParam);

	/**
	 * 查询视图
	 * @param id
	 * @return
	 */
	List<TypicalExample> getExamplesView(String id);

	List<TreeNode> GetTypicalExampleNodes(TreeNode nodeParam);
	
	/**
	 * 保存(id值为null)/修改(id值数据库中已存在)
	 * 修改时，会将当前entity的所有属性值都进行修改（包括null），如果只改不为null的值，需添加entity实体属性上加注解。
	 * @param te
	 * @return
	 */
	TypicalExample saveTypicalExample(TypicalExample te);

}
