package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志表
 */
public class ErpAuthLogParamDto implements Serializable {

    private static final long serialVersionUID = -6355681953043101703L;

    /**
     * ID
     */
    private String id;

    /**
     * 访问时间
     */
    private Date startDate;

    /**
     * 访问时间(毫秒)
     */
    private String strDate;

    /**
     * 登陆账号
     */
    private String userCode;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * IP
     */
    private String ip;

    /**
     * 访问菜单
     */
    private String fwUrl;

    /**
     * 是否有权限访问
     */
    private String isqx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFwUrl() {
        return fwUrl;
    }

    public void setFwUrl(String fwUrl) {
        this.fwUrl = fwUrl;
    }

    public String getIsqx() {
        return isqx;
    }

    public void setIsqx(String isqx) {
        this.isqx = isqx;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

	
