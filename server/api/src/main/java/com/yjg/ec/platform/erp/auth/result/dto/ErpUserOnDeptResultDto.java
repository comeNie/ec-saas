package com.yjg.ec.platform.erp.auth.result.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户与部门关系
 *
 * @author liubin
 */
public class ErpUserOnDeptResultDto implements Serializable {

	private static final long serialVersionUID = -7716338350639049570L;

	private Integer user_id;

	private Integer dept_id;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
