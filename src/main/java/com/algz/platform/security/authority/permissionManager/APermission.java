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
	@GenericGenerator(name = "ALGZGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String permissid;

	@Column(name = "PERMISSIONCODE")
	private String permisscode;

	@Column(name = "PERMISSIONNAME")
	private String permissname;

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

}
