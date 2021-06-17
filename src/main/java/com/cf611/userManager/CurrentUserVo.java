package com.cf611.userManager;

import com.algz.platform.security.authority.userManager.AUser;

public class CurrentUserVo {
	
	/**
	 * 用户ID
	 */
	private String userid;
	
	/**
	 * 用户名称
	 * name: 'Serati Ma',
	 */
	private String name;
	
	/**
	 * avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',
	 */
	private String avatar;
	
	/**
	 * title: '交互专家',
	 */
	private String title;
	
	/**
	 * group: '蚂蚁集团－某某某事业群－某某平台部－某某技术部－UED',
	 */
	private String group;
	
	/**
	 * signature: '海纳百川，有容乃大',
	 */
	private String signature;
	
	/**
	 * 标签
	 * tags: [{key: '0',label: '很有想法的',}]
	 */
	private Tag[] tags;
	
	/**
	 * unreadCount: 11,
	 */
	private Integer unreadCount;
	
	
	public void transform(AUser u) {
		this.setUserid(u.getUserid());
		this.setName(u.getUsername());
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getGroup() {
		return group;
	}



	public void setGroup(String group) {
		this.group = group;
	}



	public String getSignature() {
		return signature;
	}



	public void setSignature(String signature) {
		this.signature = signature;
	}



	public Tag[] getTags() {
		return tags;
	}



	public void setTags(Tag[] tags) {
		this.tags = tags;
	}



	public Integer getUnreadCount() {
		return unreadCount;
	}



	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}



	public class Tag{
		private String key;
		
		private String label;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
		
		
	}
	

}
