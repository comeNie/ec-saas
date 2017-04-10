package com.yjg.ec.platform.erp.auth.result.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 3.	用户角色关联表erp_user_role
 *
 * @author gus
 */
public class ErpUserRoleResultDto {
    private Integer user_id;
    private Integer role_id;
    private String login_name;
    private String role_name;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
