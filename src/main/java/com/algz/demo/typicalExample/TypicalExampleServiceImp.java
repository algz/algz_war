package com.algz.demo.typicalExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@Service
public class TypicalExampleServiceImp implements TypicalExampleService {

	private static final Logger logger=LoggerFactory.getLogger(TypicalExampleServiceImp.class);
	
	/**
	 * 用于执行自定义SQL,eg:
	 *  StringBuilder datasql=new StringBuilder();
	 *  datasql.append(" order by id desc limit "+(page-1)*10+",10) b on a.id = b.id");
        List<HandlerCash> handlerCashes = entityManager.createNativeQuery(datasql.toString(),HandlerCash.class).getResultList();
        BigInteger count = (BigInteger)entityManager.createNativeQuery(countSql.toString()).getSingleResult();
	 */
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TypicalExampleRepository repository;

	/**
	 * 查询视图
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<TypicalExample> getExamplesView(String id) {
		logger.info("查询视图");
		List<Map<String, Object>> mapList = repository.getTypicalExampleForMapList(id);
		try {
			return SpringBeanUtils.ListMapToListEntity(mapList, TypicalExample.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * List查询
	 */
	@Override
	public List<TypicalExample> getExamples(TypicalExample exampleParam) {
		logger.info("List查询");
		return repository.findAll(Example.of(exampleParam));
	}

	/**
	 * 分页排序查询
	 */
	@Override
	public Page<TypicalExample> getExamples(ProTablePage<TypicalExample> pageParam, TypicalExample exampleParam) {
		logger.info("分页排序查询");
		// 参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size
		// 设置每页数据的条数;参数3，排序,字符串参数("createDate")是对象(definition)属性名称,大小写。
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize(),
				Sort.by("createDate").descending());
		//Sort.by(Sort.Direction.DESC, "createDate")
//		pageParam.setPage(page);
		return repository.findAll(Example.of(exampleParam), pageable);// 没有数据时，返回空列表;
	}

	/**
	 * 分页复杂查询
	 */
	@Override
	public ProTablePage<TypicalExample> getSpecificationOfExamples(ProTablePage<TypicalExample> pageParam,
			TypicalExample exampleParam) {

		logger.info("分页复杂查询");
		// 排序
		// Sort sort= new Sort(Sort.Direction.ASC, "uid");
		// Pageable pageable = new PageRequest(pageIndex, pageSize,sort);
		// Pageable pageable = PageRequest.of(0, 10,Sort.by("createTime"));

		// 参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size
		// 设置每页数据的条数;参数3，排序,字符串参数("createDate")是对象(definition)属性名称,大小写。
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize(),
				Sort.by("createDate").descending());

		// 直接使用匿名内部类实现接口
		Specification<TypicalExample> specification = new Specification<TypicalExample>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TypicalExample> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				// 条件1：查询 tvName ="TV" 的数据，root.get 中的值与 TV 实体中的属性名称对应
				// predicateList.add(cb.equal(root.get("tvName").as(String.class), "TV"));

				// 条件2：查询 dateOfProduction >= start 的数据，root.get 中的 dateOfProduction 必须对应 TV
				// 中的属性
				// predicateList.add(cb.greaterThanOrEqualTo(root.get("dateOfProduction").as(Date.class),
				// start));

				// 条件3：查询 dateOfProduction <= end。
				// predicateList.add(cb.lessThanOrEqualTo(root.get("dateOfProduction").as(Date.class),
				// end));

				// 条件4：效果相当于 where (state in ('1','2','3') )
				// CriteriaBuilder.In<Object> in = cb.in(root.get("state"));//定义查询的字段(state)
				// in.value("1").value("2").value("3");
				// predicateList.add(cb.and(cb.in(in))

				if (exampleParam.getName() != null) {
					String[] stateArr = exampleParam.getName().split(",");
					// Path<Object> path = root.get("state");
					CriteriaBuilder.In<Object> in = cb.in(root.get("state"));// 定义查询的字段
					for (int i = 0; i < stateArr.length; i++) {
						in.value(stateArr[i]);// 存入值
					}
					predicateList.add(cb.and(in));// 存入条件集合里
				}

				// 返回查询结果
				Predicate[] pre = new Predicate[predicateList.size()];
				pre = predicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}
		};
		Page<TypicalExample> page = repository.findAll(specification, pageable);// 没有数据时，返回空列表
		// Page<Definition> page=
		// repository.findAll(Example.of(definitionParam),pageable);

		pageParam.setPage(page);
		return pageParam;
	}

	/**
	 * List复杂查询
	 */
	@Override
	public List<TypicalExample> getSpecificationOfExamples(TypicalExample exampleParam) {

		logger.info("List复杂查询");
		
		// 直接使用匿名内部类实现接口
		Specification<TypicalExample> specification = new Specification<TypicalExample>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TypicalExample> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				// 条件1：查询 tvName ="TV" 的数据，root.get 中的值与 TV 实体中的属性名称对应
				// predicateList.add(cb.equal(root.get("tvName").as(String.class), "TV"));

				// 条件2：查询 dateOfProduction >= start 的数据，root.get 中的 dateOfProduction 必须对应 TV
				// 中的属性
				// predicateList.add(cb.greaterThanOrEqualTo(root.get("dateOfProduction").as(Date.class),
				// start));

				// 条件3：查询 dateOfProduction <= end。
				// predicateList.add(cb.lessThanOrEqualTo(root.get("dateOfProduction").as(Date.class),
				// end));

				// 条件4：效果相当于 where (state in ('1','2','3') )
				// CriteriaBuilder.In<Object> in = cb.in(root.get("state"));//定义查询的字段(state)
				// in.value("1").value("2").value("3");
				// predicateList.add(cb.and(cb.in(in))

				if (exampleParam.getName() != null) {
					String[] stateArr = exampleParam.getName().split(",");
					// Path<Object> path = root.get("state");
					CriteriaBuilder.In<Object> in = cb.in(root.get("state"));// 定义查询的字段
					for (int i = 0; i < stateArr.length; i++) {
						in.value(stateArr[i]);// 存入值
					}
					predicateList.add(cb.and(in));// 存入条件集合里
				}

				// 返回查询结果
				Predicate[] pre = new Predicate[predicateList.size()];
				pre = predicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}
		};
		return repository.findAll(specification);// 没有数据时，返回空列表
	}

	/**
	 * 获取树结点列表
	 */
	@Override
	public List<TreeNode> GetTypicalExampleNodes(TreeNode nodeParam) {
		logger.info("获取树结点列表");
		
		TreeNode node = new TreeNode(nodeParam.getKey());
		RecursionTreeNode(node);
		return node.getChildren();
	}

	/**
	 * 递归子结点
	 * 
	 * @param root
	 */
	private void RecursionTreeNode(TreeNode root) {
		TypicalExample teParam = new TypicalExample();
//		indicatorParam.setParentId(root.getKey());
		List<TypicalExample> teList = repository.findAll(Example.of(teParam));
		if (teList.size() != 0) {
			root.setIsLeaf(false);
			root.setChildren(new ArrayList<TreeNode>());
			for (TypicalExample it : teList) {
				TreeNode node = new TreeNode(it.getId(), it.getName());
//				Map<String,String> m=new HashMap<String,String>();
//				m.put("description", it.getDescription());
//				m.put("parentId", it.getParentId());
//				node.setExtProps(m); //自定义属性
				root.getChildren().add(node);
				RecursionTreeNode(node);
			}
		} else {
			root.setIsLeaf(true);
		}
	}

	/**
	 * 保存
	 */
	@Transactional
	@Override
	public TypicalExample saveTypicalExample(TypicalExample te) {
		logger.info("保存");
		return repository.save(te);
	}
}
