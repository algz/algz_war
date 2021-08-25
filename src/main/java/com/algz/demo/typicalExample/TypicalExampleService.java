package com.algz.demo.typicalExample;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cf611.util.ProTablePage;

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

}
