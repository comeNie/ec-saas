package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;

/**
 * 角色权限关系表erp_role_authority
 *
 * @author gus
 */
public class ErpRoleAuthorityParamDto extends ErpBaseInfo {

	private static final long serialVersionUID = 2098027168853901022L;

	private Integer role_id;

	private Integer authority_id;
	// 权限码
	private String code;
	// 权限描述
	private String description;
	// 角色名称
	private String role_name;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(Integer authority_id) {
		this.authority_id = authority_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
