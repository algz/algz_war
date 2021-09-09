package com.cf611.requirementDefinition.definition;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Modifying;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="CF_DEFINITIONS")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**
 * save()方法，会先select查询，然后匹配对象各个值有没有改变，没有改变的字段则不update此字段。
 * 
 * @DynamicUpdate 的作用并不是更新指定字段，而是更新变化的字段；
数据库中表字段对应到对象中的成员变量，如果请求过来的参数字段被封装成对象，直接使用save（）方法，请求url中没有涉及到的字段默认为null,
如果这个字段在数据库中已经有值了会被null覆盖。因为，save()的对象中这个字段跟数据库中的值不同。
解决方法大概两种，一是自已写一个方法先findOne/findById查出数据库对象，把想变化的字段赋值回对象中，再save();第一种是使用sql
 * @author algz
 *
 */
@DynamicInsert(true) //insert语句时，null值不添加。同时需设置一个@Column的insertable=false属性，才能全局生效。
@DynamicUpdate(true) //update语句时，null值不更新。
public class Definition {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator") //org.hibernate.id.UUIDGenerator
	@GeneratedValue(generator="ALGZGenerator")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="STATE")
	private String state;
	
	
	@Column(name="CREATEDATE",updatable = false,insertable = false)
	private String createDate;
	
	//mappedBy="DefinitionDetail对象的definitionId属性"
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "definitionId")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //解决无法接收前端参数
	private List<DefinitionDetail> detailList;
	
	@Transient
	private String creatorName;
	
	
	
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<DefinitionDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DefinitionDetail> detailList) {
		this.detailList = detailList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}



	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	

	

}
