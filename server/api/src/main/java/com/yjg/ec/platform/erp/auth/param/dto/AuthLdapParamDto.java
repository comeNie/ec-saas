package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;

/**
 * 登录ldap的dto
 */
public class AuthLdapParamDto extends ErpBaseInfo {

	private static final long serialVersionUID = -6646955470548167144L;

	/**
	 * 登录名
	 */
	private String userCn;

	/**
	 * 登录密码
	 */
	private String credentials;

	public String getUserCn() {
		return userCn;
	}

	public void setUserCn(String userCn) {
		this.userCn = userCn;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
