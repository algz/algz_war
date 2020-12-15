/**
 * 
 */
package com.algz.platform.security.authority.userManager;

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
public class AUser implements UserDetails  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String userid;
	

	@Column(name = "USERNAME")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@JsonIgnore //设置 @JsonIgnore,restFul返回时,不读取此属性,可用于C# DataTable生成.
	 //不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name="ALGZ_USER_ROLE",
		joinColumns={@JoinColumn(name="USERID")},
		inverseJoinColumns={@JoinColumn(name="ROLEID")})
	private List<ARole> authorities;
	
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



}
