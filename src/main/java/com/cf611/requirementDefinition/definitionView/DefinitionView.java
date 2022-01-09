package com.cf611.requirementDefinition.definitionView;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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



/**
 * @DynamicUpdate 的作用并不是更新指定字段，而是更新变化的字段；
数据库中表字段对应到对象中的成员变量，如果请求过来的参数字段被封装成对象，直接使用save（）方法，请求url中没有涉及到的字段默认为null,
如果这个字段在数据库中已经有值了会被null覆盖。因为，save()的对象中这个字段跟数据库中的值不同。
解决方法大概两种，一是自已写一个方法先findOne/findById查出数据库对象，把想变化的字段赋值回对象中，再save();第一种是使用sql
 * @author algz
 *
 */
@Entity
@Table(name="CF_DEFINITIONS_view")
public class DefinitionView {


	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="CREATEDATE",updatable = false,insertable = false)
	private String createDate;
	
	@Column(name="CREATORNAME")
	private String creatorName;
	
	/**
	 * 接收人
	 */
	@Column(name="RECEIVER")
	private String receiver;
	
	/**
	 * 接收时间
	 */
	@Column(name="RECEIVEDATE")
	private String receiveDate;
	
	/**
	 * 接收人中文名称
	 */
	@Column(name="RECEIVERNAME")
	private String receiverName;
	
	/**
	 * 判定文件ID
	 */
	@Column(name="REGULATIONFILEID")
	private String regulationFileId;
	
	/**
	 * 判定文件名称
	 */
	@Column(name="REGULATIONFILENAME")
	private String regulationFileName;
	
	@Column(name="ARCHIVEFILEID")
	private String archiveFileId;
	
	@Column(name="ARCHIVEFILENAME")
	private String archiveFileName;
	
	//mappedBy="DefinitionDetail对象的definitionId属性"
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "definitionId")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //解决无法接收前端参数
	private List<DefinitionDetail> detailList;
	
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<DefinitionDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DefinitionDetail> detailList) {
		this.detailList = detailList;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverDate() {
		return receiveDate;
	}

	public void setReceiverDate(String receiverDate) {
		this.receiveDate = receiverDate;
	}



	public String getRegulationFileId() {
		return regulationFileId;
	}

	public void setRegulationFileId(String regulationFileId) {
		this.regulationFileId = regulationFileId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPathCodeName() {
		return regulationFileName;
	}

	public void setPathCodeName(String pathCodeName) {
		this.regulationFileName = pathCodeName;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	


	

	

}
