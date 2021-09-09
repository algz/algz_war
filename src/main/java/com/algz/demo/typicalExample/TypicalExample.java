package com.algz.demo.typicalExample;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.definition.Definition;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@Table(name="Example")
@DynamicInsert(true) //insert语句时，null值不添加。同时需设置一个@Column的insertable=false属性，才能全局生效。
@DynamicUpdate(true) //update语句时，null值不更新。
public class TypicalExample {

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "com.algz.platform.common.sql.AIDGenerator")//org.hibernate.id.UUIDGenerator
	@GeneratedValue(generator="ALGZGenerator")
	public String id;
	
	@Column(name = "NAME")
	public String name;

	//对应的参数为 懒加载(LAZY/实时加载EAGER)、级联操作、子表外键(mappedBy="DefinitionDetail类的definitionId属性"
	@OneToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "definitionId") //表示级练操作
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //仅JSON写的权限，作用是不进行JSON转换和不传到前端。解决后端无法接收前端参数.
	private List<DefinitionDetail> detailList;
	
	//1.重复列
	@Column(name="DEFINITIONID")
	private String definitionId;
	
	//1.重复列
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	//name="字段名"；映射重复的列，必须其它重复列的属性设置insertable = false ,updatable = false，让Hibernate知道引用哪值。
    @JoinColumn(name="DEFINITIONID",insertable = false ,updatable = false)
	private Definition defintion;
	
	//不映射
	@Transient
	public String test;
	
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

	public List<DefinitionDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DefinitionDetail> detailList) {
		this.detailList = detailList;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	
	
}
