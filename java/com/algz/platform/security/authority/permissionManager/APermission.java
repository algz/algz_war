package com.algz.platform.security.authority.permissionManager;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.algz.platform.security.authority.roleManager.ARole;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ALGZ_PERMISSION")
public class APermission {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String permissid;

	/**
	 * 权限URL访问路径
	 */
	@Column(name = "PERMISSIONCODE")
	private String permisscode;

	@Column(name = "PERMISSIONNAME")
	private String permissname;

	/**
	 * 权限类型（菜单）
	 */
	@Column(name = "PERMISSIONKIND")
	private String permissionkind;
	
	/**
	 * 权限组件存储路径
	 */
	@Column(name = "PERMISSIONPATH")
	private String permissionpath;
	
	@Column(name = "ICON")
	private String icon;	

	@Column(name="CREATEDATE",insertable = false,updatable = false)
	private String createDate;
	
	@JsonBackReference
	// 不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could
	// not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "ALGZ_ROLE_PERMISSION", joinColumns = {
			@JoinColumn(name = "PERMISSIONID") }, inverseJoinColumns = { @JoinColumn(name = "ROLEID") })
	private List<ARole> roleList;

	public APermission() {
		
	}
	
	public APermission(String permissname ) {
		this.permissname=permissname;
	}
	
	public String getPermissid() {
		return permissid;
	}

	public void setPermissid(String permissid) {
		this.permissid = permissid;
	}

	public String getPermisscode() {
		return permisscode;
	}

	public void setPermisscode(String permisscode) {
		this.permisscode = permisscode;
	}

	public String getPermissname() {
		return permissname;
	}

	public void setPermissname(String permissname) {
		this.permissname = permissname;
	}

	public List<ARole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<ARole> roleList) {
		this.roleList = roleList;
	}

	public String getPermissionkind() {
		return permissionkind;
	}

	public void setPermissionkind(String permissionkind) {
		this.permissionkind = permissionkind;
	}

	public String getPermissionpath() {
		return permissionpath;
	}

	public void setPermissionpath(String permissionpath) {
		this.permissionpath = permissionpath;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
}
