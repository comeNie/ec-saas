package com.yjg.ec.platform.login.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhangyunfei on 01/01/2017.
 */
public class LoginUserPasswordFreeParamDto {

    private String type;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "sessionId不能为空")
    private String sessionId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
