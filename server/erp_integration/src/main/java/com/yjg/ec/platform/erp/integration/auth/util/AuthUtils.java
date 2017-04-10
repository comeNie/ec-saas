package com.yjg.ec.platform.erp.integration.auth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthUtils {
    private final static Integer AJAX_LOGIN_CODE = 300;
    private final static Integer AJAX_INVALID_USER_CODE = 400;
    private final static Integer AJAX_UNAUTHORIZED_CODE = 403;
    private final static Integer AJAX_EXCEPTION_CODE = 500;

    private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cliCookie = cookie.getName();
                if (cliCookie.equals(name)) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
        return loginCookie;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie c = getCookie(request, name);
        return c == null ? null : c.getValue();
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String domain, String path, String name) {
        Cookie[] cookies = request.getCookies();
        if ((cookies != null) && (cookies.length > 0)) {
            for (int i = 0; i < cookies.length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(name)) {
                    cookie.setMaxAge(0);
                    cookie.setPath(path);
                    cookie.setDomain(domain);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null &&
                request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest");
    }

    public static void ajaxLoginResponse(HttpServletResponse httpServletResponse) {
        ajaxErrorResponse(httpServletResponse, AJAX_LOGIN_CODE);
    }

    public static void ajaxInvalidUserResponse(HttpServletResponse httpServletResponse) {
        ajaxErrorResponse(httpServletResponse, AJAX_INVALID_USER_CODE);
    }

    public static void ajaxUnauthorizedResponse(HttpServletResponse httpServletResponse) {
        ajaxErrorResponse(httpServletResponse, AJAX_UNAUTHORIZED_CODE);
    }

    public static void ajaxExceptionResponse(HttpServletResponse httpServletResponse) {
        ajaxErrorResponse(httpServletResponse, AJAX_EXCEPTION_CODE);
    }

    public static void ajaxErrorResponse(HttpServletResponse httpServletResponse, Integer errorCode) {
        Map<String, Integer> map = new HashMap<>();
        map.put("AuthErrorCode", errorCode);
        renderJson(httpServletResponse, map);
    }

    public static void renderJson(HttpServletResponse httpServletResponse, Object data) {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(httpServletResponse.getWriter(), data);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static String parseLoginName(String value) throws AuthenticationException {
        if(!StringUtils.hasText(value) || value.indexOf("_") < 0){
            logger.error("value or decodingKey is empty, value:" + value);
            throw new IncorrectCredentialsException();
        }
        String ticket;
        try {
            ticket = value.split("_")[0];
        } catch (Exception e) {
            logger.error("get authentication ticket using DotNetAuthenticationUtil error, value:" + value, e);
            throw new IncorrectCredentialsException(e);
        }

        if (ticket == null) {
            logger.error("ticket get from cookie is null, value:" + value);
            throw new IncorrectCredentialsException();
        }

        return ticket;
    }
}

