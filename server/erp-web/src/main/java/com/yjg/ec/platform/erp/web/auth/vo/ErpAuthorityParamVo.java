package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限参数vo
 *
 * @author gus
 */
public class ErpAuthorityParamVo extends BaseGridVo {

    private String code;
    private String description;

    /**
     * 是否绑定，页面使用，1：绑定，0：未绑定
     */
    private Integer bindFlag;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
