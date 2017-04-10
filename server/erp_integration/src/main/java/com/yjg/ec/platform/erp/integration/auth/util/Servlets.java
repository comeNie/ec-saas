/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.yjg.ec.platform.erp.integration.auth.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Http与Servlet工具类.
 *
 * @author calvin/thinkgem
 * @version 2014-8-19
 */
public class Servlets {

    // -- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    // 静态文件后缀
    private final static String[] staticFiles = {".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".bmp", ".ico", ".swf", ".psd", ".htc", ".htm", ".html", ".crx", ".xpi", ".exe", ".ipa", ".apk"};

    /**
     * 获取当前请求对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        try{
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     *
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (staticFiles == null) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                        + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")) {
            return true;
        }
        return false;
    }
}
