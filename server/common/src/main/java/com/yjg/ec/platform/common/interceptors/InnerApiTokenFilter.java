package com.yjg.ec.platform.common.interceptors;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.util.ResponseJsonUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangyunfei on 20/12/2016.
 */
@Component
@WebFilter
public class InnerApiTokenFilter implements Filter {

    @Autowired
    private PasswordEncoder tokenPasswordEncoder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        if (!StringUtils.contains(uri, "/inner/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String header = request.getHeader("InnerApiToken");
        StringBuffer url = request.getRequestURL();
        if (StringUtils.isNotBlank(header) && tokenPasswordEncoder.matches(url, header)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Result result = Result.buildExceptionResult("UnAuthority");
            ResponseJsonUtil.responseJson((HttpServletResponse) servletResponse,result);
        }
    }

    @Override
    public void destroy() {

    }

}
