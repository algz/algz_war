/**
 * 
 */
package com.algz.platform.security.authority.userManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.algz.platform.security.authority.roleManager.ARole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author algz
 *
 */
@Entity
@Table(name="ALGZ_USER")
@DynamicInsert(true) //insert语句时，null值不添加。同时需设置一个@Column的insertable=false属性，才能全局生效。
@DynamicUpdate(true) //update语句时，null值不更新。
public class AUser implements UserDetails  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String userid;
	

	/**
	 * 登录名称
	 */
	@Column(name = "USERNAME")
	private String username;
	
	/**
	 * 密码
	 */
	@JsonIgnore
	@Column(name="PASSWORD")
	private String password;
	
	/**
	 * 显示名称（或中文名称）
	 */
	@Column(name="NAME")
	private String name;
	
	@JsonIgnore //设置 @JsonIgnore,restFul返回时,不读取此属性,可用于C# DataTable生成.
	 //不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name="ALGZ_USER_ROLE",
		joinColumns={@JoinColumn(name="USERID")},
		inverseJoinColumns={@JoinColumn(name="ROLEID")})
	private List<ARole> authorities;
	
	@Column(name="CREATEDATE",updatable = false,insertable = true)
	private String createDate;
	
	/**
	 * 只读，获得角色名称
	 */
	@Transient
	private String authoritienName;
	
	/**
	 * 只读，获得角色名称
	 */
	@Transient
	private String authoritieDesc;
	
	public String getAuthoritienName() {
		List<String> list=new ArrayList<String>();
		if(authorities!=null) {
			authorities.forEach(x->{
				list.add(x.getAuthority());
			});
			return String.join(",", list);
		}
		return this.authoritienName;
	}
	
	
	
	public void setAuthoritienName(String authoritienName) {
		this.authoritienName = authoritienName;
	}



	/**
	 * 只读，获得角色说明
	 */
	@Transient
	public String getAuthoritieDesc() {
		List<String> list=new ArrayList<String>();
		if(authorities!=null) {
			authorities.forEach(x->{
				list.add(x.getDescription());
			});
			return String.join(",", list);
		}
		return authoritieDesc;
	}


	public void setAuthoritieDesc(String authoritieDesc) {
		this.authoritieDesc = authoritieDesc;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<ARole> authorities) {
		this.authorities = authorities;
	}




	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



}
