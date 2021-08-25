package com.algz.demo.typicalExample;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TypicalExampleRepository  extends JpaRepository<TypicalExample,String>,JpaSpecificationExecutor<TypicalExample>{

	/**
	 * 自定义查询
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM CF_DEFINITIONDETAIL_VIEW where definitionId=?1")
	public List<Map<String,Object>> getTypicalExampleForMapList(String id);
	
	/**
	 * 自定义实体查询
	 * te对象的id属性。
	 * ?#{#te.id}  =  :?#{#te.id}
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM CF_DEFINITIONDETAIL_VIEW where definitionId=?#{#te.id}")
	public List<Map<String,Object>> getTypicalExampleForMapList(TypicalExample te);
	
	/**
	 * 自定义查询，分页
	 * @param pageable
	 * @return
	 */
	@Query(nativeQuery = true,value = "select * from algz_user_view",
			countQuery = "select count(1) from algz_user_view ")
	Page<Map<String,Object>> findUsersView(Pageable pageable);
	
	/**
	 * DDL语句（insert,update,delete)。
	 * 必须添加 @ Modifying 注解，不然会报异常.(ORA-01002: 提取违反顺序)
	 * @param userid
	 * @param roleid
	 */
	@Modifying
	@Query(nativeQuery=true,value="insert into ALGZ_USER_ROLE (userid,roleid)values(:userid,:roleid)")
	void addUserRole(String userid,String roleid);
	
}
