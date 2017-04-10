package com.yjg.ec.platform.erp.auth.result.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;

/**
 * 角色权限绑定关系dto（ztree复选框页面使用）
 *
 * @author Mengzipeng
 * @date 2015/9/28
 */
public class ErpAuthorityRelationResultDto extends ErpBaseInfo {

	private static final long serialVersionUID = -6436153080954785992L;

	/**
	 * 复选框id
	 */
	private Integer id;

	/**
	 * 父id
	 */
	private Integer pId;

	/**
	 * 复选框名称
	 */
	private String name;

	/**
	 * 复选框是否可勾选
	 */
	private boolean open = true;

	/**
	 * 复选框默认是否勾选
	 */
	private boolean checked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
