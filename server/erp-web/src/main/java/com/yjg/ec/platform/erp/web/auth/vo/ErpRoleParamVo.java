package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色参数vo
 *
 * @author gus
 */
public class ErpRoleParamVo extends BaseGridVo {

    private String role_name;
    private String description;

    private Integer status;

    /**
     * 是否绑定，页面使用，1：绑定，0：未绑定
     */
    private Integer bindFlag;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
