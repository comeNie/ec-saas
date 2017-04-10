package com.yjg.ec.platform.erp.integration.auth.web;


import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -3192277192573420197L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String account;

    public ShiroUser() {

    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return this.account;
    }

    /**
     * 重载equals,只计算account;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShiroUser other = (ShiroUser) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        return true;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getAccount()).toHashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}