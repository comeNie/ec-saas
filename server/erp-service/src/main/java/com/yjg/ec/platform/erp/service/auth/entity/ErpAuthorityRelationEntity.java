package com.yjg.ec.platform.erp.service.auth.entity;

import java.util.Date;

public class ErpAuthorityRelationEntity {

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

	private Integer create_id;

	private Date create_time;

	private Integer modify_id;

	private Date modify_time;

	private Integer status = 1;

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

	public Integer getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getModify_id() {
		return modify_id;
	}

	public void setModify_id(Integer modify_id) {
		this.modify_id = modify_id;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
