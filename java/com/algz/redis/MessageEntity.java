package com.algz.redis;

import java.io.Serializable;

public class MessageEntity implements Serializable {
	
	private static final long serialVersionUID = 8632296967087444509L;

    private String id;
    
    private String content;

    public MessageEntity() {
        super();
    }

    public MessageEntity(String id, String content) {
        super();
        this.id = id;
        this.content = content;
    }

    // getter, setter 

    
    @Override
    public String toString() {
        return "MessageEntity [id=" + id + ", content=" + content + "]";
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
