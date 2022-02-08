package com.cf611.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.algz.platform.utility.JsonUtils;

/**
 * @author algz
 *
 * @param <T>
 */
public class ProTablePage<T> {

	
	/**
	 * 当前页数
	 */
	private Integer current;
	
	/**
	 * 总页数
	 */
	private Integer pageSize;
	
	/**
	 * 是否成功
	 */
	private Boolean success;
	
	/**
	 * 记录总数
	 */
	private Long total;
	
	/**
	 * 记录。
	 */
	private List<T> data;
	
//	private Page<T> page;
	
	/**
	 * 排序参数
	 * 语法：sorter:{dataIndex:"ascend"/"descend"
	 * 例：sorter: {"callNo":"ascend"} 
	 */
	private String sorter;
	
//	private HashMap<String,String> sorter;

	private String filter;
	
	
	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	/**
	 * 	int total = pageT.getTotalElements();	// 获取记录的总条数
		int count = pageT.getSize();			// 获取每页的记录条数
		int page = pageT.getNumber();			//	获取当前是第几页
		int totalPage = pageT.getTotalPages();	//	获取总页数
		List<T> list = pageT.getContent();		// 获取记录的集合列表
	 * @param page
	 */
	public void setPage(Page<T> page) {
//		this.page=page;
		this.setData(page.toList()); //记录集合
		this.setPageSize(page.getTotalPages()); //页数
		this.setTotal(page.getTotalElements()); //记录总数
		this.setSuccess(true);
		this.setCurrent(page.getNumber());
	}

//	public Page<T> getPage() {
//		return page;
//	}

	public Map<String,Object> getSorter() {
		Map<String,Object> m=JsonUtils.jsonToMap(this.sorter);
		return m;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getFilter() {
		return filter;
	}



	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
