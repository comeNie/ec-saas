package com.yjg.ec.platform.common.domain;

import java.io.Serializable;

public class LogInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4685825272125398615L;

	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 1:患者 2:医生
	 */
	private int userType;
	
	/**
	 * 模块
	 */
	private String modelName;
	
	/**
	 * 日志描述
	 */
	private String desc;
    
    public LogInfo() {
    }
    
    public LogInfo(Long userId, int userType, String modelName, String desc) {
        this.userId = userId;
        this.userType = userType;
        this.modelName = modelName;
        this.desc = desc;
    }
    
    public LogInfo(String loginName, int userType, String modelName, String desc) {
        this.loginName = loginName;
        this.userType = userType;
        this.modelName = modelName;
        this.desc = desc;
    }
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
