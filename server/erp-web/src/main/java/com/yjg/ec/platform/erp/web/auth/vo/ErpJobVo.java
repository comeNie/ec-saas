package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErpJobVo extends ErpBaseVo {

    private String job_name;

    private String description;

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
