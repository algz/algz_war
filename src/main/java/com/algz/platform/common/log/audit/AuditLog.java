//package com.algz.platform.common.log.audit;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//
//@Table(name="ALGZ_AUDIT_LOG")
//@Entity
//public class AuditLog {
//
//	@Id
//	@GenericGenerator(name="ALGZGenerator",strategy = "com.algz.platform.common.sql.AIDGenerator")
//	@GeneratedValue(generator = "ALGZGenerator")
//	@Column(name="ID",length=32)
//	private String id;
//	
//	@Column(name="Name")
//	private String name;
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	
//}
