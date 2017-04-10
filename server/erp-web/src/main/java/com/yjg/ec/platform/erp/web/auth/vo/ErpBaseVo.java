package com.yjg.ec.platform.erp.web.auth.vo;

import java.beans.Transient;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.erp.web.auth.utils.DateUtil;

public class ErpBaseVo {

	private Integer id;

	private String code;

	private Date create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Transient
	public String getCreateDateStr() {
		return DateUtil.date2String(create_time);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
