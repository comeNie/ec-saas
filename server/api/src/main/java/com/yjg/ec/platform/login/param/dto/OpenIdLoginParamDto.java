package com.yjg.ec.platform.login.param.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhangyunfei on 01/01/2017.
 */
public class OpenIdLoginParamDto {

    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "code不能为空！")
    private String code;

    @NotBlank(message = "oauthServerType不能为空！")
    private String oauthServerType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOauthServerType() {
        return oauthServerType;
    }

    public void setOauthServerType(String oauthServerType) {
        this.oauthServerType = oauthServerType;
    }
}
