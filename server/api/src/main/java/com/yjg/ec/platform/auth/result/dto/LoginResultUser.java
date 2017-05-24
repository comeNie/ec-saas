package com.yjg.ec.platform.auth.result.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangyunfei on 01/01/2017.
 */
public class LoginResultUser implements Serializable {

	private static final long serialVersionUID = 5784446636400882693L;

	private String type;

	private String username;

	private String password;

	private List<String> authList;

	private Object userInfo;

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}

}
