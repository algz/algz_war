package com.sany.airmodelManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="S_AIRMODEL")
public class AirModel {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="MODELNAME")
	private String modelname;
	
//	@Column(name="EDITOR")
//	private String editor;
	
	//CREATEDATE
	@Column(name="AMPLITUDETYPE")
	private String amplitudetype;
	
	@Column(name="COMPRESSIONMODE")
	private String compressionmode;
	
	@Column(name="ZJYWZ")
	private String zjywz;
	
	@Column(name="ZG_KIND")
	private String zgkind;
	
	@Column(name="JYJYWZ")
	private String jyjywz;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

//	public String getEditor() {
//		return editor;
//	}
//
//	public void setEditor(String editor) {
//		this.editor = editor;
//	}

	public String getAmplitudetype() {
		return amplitudetype;
	}

	public void setAmplitudetype(String amplitudetype) {
		this.amplitudetype = amplitudetype;
	}

	public String getCompressionmode() {
		return compressionmode;
	}

	public void setCompressionmode(String compressionmode) {
		this.compressionmode = compressionmode;
	}

	public String getZjywz() {
		return zjywz;
	}

	public void setZjywz(String zjywz) {
		this.zjywz = zjywz;
	}

	public String getZgkind() {
		return zgkind;
	}

	public void setZgkind(String zgkind) {
		this.zgkind = zgkind;
	}

	public String getJyjywz() {
		return jyjywz;
	}

	public void setJyjywz(String jyjywz) {
		this.jyjywz = jyjywz;
	}
	
	

}
