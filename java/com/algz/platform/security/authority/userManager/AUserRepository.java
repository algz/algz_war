package com.algz.platform.security.authority.userManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * CrudRepository提供了一些简单的增删查改功能，接口定义如下。 
 * PagingAndSortingRepository继承于CrudRepository，除了具有CrudRepository接口的能力外，还新增了分页和排序的功能
 * JpaRepository继承于PagingAndSortingRepository，所以它传递性地拥有了以上接口的所有方法，同时，它还继承了另外一个QueryByExampleExecutor接口，拥有了该接口匹配指定样例的能力，JpaRepository接口定义如下。 
 * 
 * 
 * (1)方法签名查询-创建Repository时,Spring Data会检查Respository接口的所有方法,解析方法名,动态实现方法.
 * 
 * @author algz
 *
 */
public interface AUserRepository extends JpaRepository<AUser, String> {

	/**
	 * 按命名规范定义。（由jpa完成接口实现。）
	 * @param username
	 * @return
	 */
	AUser findByUsername(String username);
	
	/**
	 * 自定义查询，分页
	 * @param pageable
	 * @return
	 */
	@Query(nativeQuery = true,value = "select * from algz_user_view",
			countQuery = "select count(1) from algz_user_view ")
	Page<Map<String,Object>> findUsersView(Pageable pageable);
	
	/**
	 * 添加用户与角色关联
	 * @param userid
	 * @param roleid
	 */
	@Modifying
	@Query(nativeQuery=true,value="insert into ALGZ_USER_ROLE (userid,roleid)values(:userid,:roleid)")
	void addUserRole(String userid,String roleid);

	@Modifying
	@Query(nativeQuery=true,value="delete from ALGZ_USER_ROLE where userid=?1")
	void deleteUserRoleForUserid(String userid);
}