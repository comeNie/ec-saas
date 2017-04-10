package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;
import com.yjg.ec.platform.constants.StatusType;

/**
 * 角色表erp_role
 *
 * @author gus
 */
public class ErpRoleParamDto extends ErpBaseInfo {

	private static final long serialVersionUID = -2520265324423988770L;

	private String role_name;

	private String description;

	/**
	 * 是否绑定，页面使用，1：绑定，0：未绑定
	 */
	private Integer bindFlag;

	/**
	 * 有效状态（页面显示）
	 */
	private String statusName;

	public String getStatusName() {
		return StatusType.getDescriptionByKey(getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBindFlag() {
		return bindFlag;
	}

	public void setBindFlag(Integer bindFlag) {
		this.bindFlag = bindFlag;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
