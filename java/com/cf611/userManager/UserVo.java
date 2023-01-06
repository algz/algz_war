package com.cf611.userManager;

import java.util.List;

public class UserVo {

	private String userName;
	
	private String password;
	
	/**
	 * mobile,
	 */
	private String type;
	
	private String[] currentAuthority;
	
	private String status;
	

	public String[] getCurrentAuthority() {
		return currentAuthority;
	}

	public void setCurrentAuthority(String[] currentAuthority) {
		this.currentAuthority = currentAuthority;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
