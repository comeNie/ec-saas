package com.yjg.ec.platform.customer.param.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CustomerUserParamDto implements Serializable {

	private static final long serialVersionUID = 5634505561701212151L;

	private Long id;

	@NotBlank(message = "用户名不能为空")
	private String username;

	private String password;

	private String wechatOpenId;

	private String wechatUnionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getWechatUnionId() {
		return wechatUnionId;
	}

	public void setWechatUnionId(String wechatUnionId) {
		this.wechatUnionId = wechatUnionId;
	}

}
