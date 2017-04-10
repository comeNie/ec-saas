package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 1.	用户表erp_use
 *
 * @author gus
 */
public class ErpUserVo {
    private Integer id;
    private String login_name;
    private String password;
    private Integer system_id;
    private Date create_time;
    private Integer isAlive;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
