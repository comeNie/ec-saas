package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;

public class ErpDeptParamDto extends ErpBaseInfo {

	private static final long serialVersionUID = 8664379092290382930L;

	private String dept_name;

	private String description;

	private Integer parent_id;

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
