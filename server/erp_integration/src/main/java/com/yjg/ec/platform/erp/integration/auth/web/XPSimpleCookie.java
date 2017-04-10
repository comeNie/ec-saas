package com.yjg.ec.platform.erp.integration.auth.web;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.servlet.SimpleCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XPSimpleCookie extends SimpleCookie{

    public XPSimpleCookie(){
        super();
    }
    
    @Override
    public String readValue(HttpServletRequest request, HttpServletResponse response) {
        String value = request.getParameter(getName());
        if(StringUtils.hasText(value)){
            return value;
        }else{
            return super.readValue(request, response);
        }
    }
}
