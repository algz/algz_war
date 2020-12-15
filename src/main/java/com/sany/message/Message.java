package com.sany.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="S2_DESIGN_MESSAGE")
public class Message {

	@Id
	@Column(name="ID")
	public String id;
	
	@Column(name="CNAME")
	public String cname;
	
	@Column(name="EDITOR")
	public String editor;
	
	@Column(name="KIND")
	public String kind;
	
	@Column(name="CONTENT")
	public String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
