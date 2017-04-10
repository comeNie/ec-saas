package com.yjg.ec.platform.erp.auth.result.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yjg.ec.platform.api.result.dto.ErpBaseInfo;
import com.yjg.ec.platform.constants.StatusType;

import java.util.Date;

/**
 * 1. 用户表erp_use
 *
 * @author gus
 */
public class ErpUserResultDto extends ErpBaseInfo {

	private static final long serialVersionUID = -1814657552301207466L;

	private Integer id;

	private String login_name;

	private Integer system_id;

	private Date create_time;

	private Integer isAlive;

	private Integer dept_id;

	private Integer job_id;

	private String dept_code;

	private String job_code;

	private String statusName;

	public String getStatusName() {
		return StatusType.getDescriptionByKey(isAlive);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Integer getSystem_id() {
		return system_id;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Integer isAlive) {
		this.isAlive = isAlive;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
