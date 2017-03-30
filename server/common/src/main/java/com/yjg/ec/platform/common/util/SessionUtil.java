package com.yjg.ec.platform.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyunfei on 01/12/2016.
 */
public class SessionUtil {

    public static Object getAttribute(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

}
