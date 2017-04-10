package com.yjg.ec.platform.api.result.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liubin
 */
public class ErpBaseInfo implements Serializable {

    private static final long serialVersionUID = -2127844034775490773L;

    private Integer id;

    private String code;

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

    public Integer getCreate_id() {
        return create_id;
    }

    public void setCreate_id(Integer create_id) {
        this.create_id = create_id;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
