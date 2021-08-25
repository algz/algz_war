package com.algz.platform.security.authority.roleManager;

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
import org.springframework.security.core.GrantedAuthority;

import com.algz.platform.security.authority.permissionManager.APermission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ALGZ_ROLE")
public class ARole implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867856932923185397L;

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="ALGZGenerator")
	private String roleid;
	
	/**
	 * 角色名称
	 */
	@Column(name="NAME")
	private String authority;
	
	/**
	 * 角色中文名称
	 */
	@Column(name="CNAME")
	private String cname;
	
	/**
	 * 角色描述
	 */
	@Column(name="DESCRIPTION")
	private String description;

	/**
	 * jackson中的@JsonBackReference和@JsonManagedReference，以及@JsonIgnore均是为了解决对象中存在双向引用导致的无限递归（infinite recursion）问题。这些标注均可用在属性或对应的get、set方法中。  

@JsonBackReference和@JsonManagedReference：这两个标注通常配对使用，通常用在父子关系中。
@JsonBackReference标注的属性在序列化（serialization，即将对象转换为json数据）时，会被忽略（即结果中的json数据不包含该属性的内容）。
@JsonManagedReference标注的属性则会被序列化。

在序列化时，@JsonBackReference的作用相当于@JsonIgnore，此时可以没有@JsonManagedReference。
但在反序列化（deserialization，即json数据转换为对象）时，如果没有@JsonManagedReference，则不会自动注入@JsonBackReference标注的属性（被忽略的父或子）；
如果有@JsonManagedReference，则会自动注入自动注入@JsonBackReference标注的属性。  

@JsonIgnore：直接忽略某个属性，以断开无限递归，序列化或反序列化均忽略。当然如果标注在get、set方法中，则可以分开控制，序列化对应的是get方法，反序列化对应的是set方法。在父子关系中，当反序列化时，@JsonIgnore不会自动注入被忽略的属性值（父或子），这是它跟@JsonBackReference和@JsonManagedReference最大的区别。  
	 */
	@JsonManagedReference
	 //不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name="ALGZ_ROLE_PERMISSION",
		joinColumns={@JoinColumn(name="ROLEID")},
		inverseJoinColumns={@JoinColumn(name="PERMISSIONID")})
	private List<APermission> permissionList;
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	
	




	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<APermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<APermission> permissionList) {
		this.permissionList = permissionList;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ARole [roleid=" + roleid + ", authority=" + authority + ", description=" + description
				+ ", permissionList=" + permissionList + "]";
	}

	
}
