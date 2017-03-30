package com.yjg.ec.platform.common.interceptors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangyunfei on 14/12/2016.
 */
@Component
@WebFilter
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CorsFilter implements Filter {

    @Value("${cors_domain}")
    private String corsDomain;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        if (request.getRequestURI() == null || request.getRequestURI().indexOf("/inner/") != -1) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String[] corsDomains = StringUtils.split(corsDomain, ",");

        if (ArrayUtils.isNotEmpty(corsDomains)) {
            String origin = request.getHeader("Origin");

            for (String cd : corsDomains) {
                if (StringUtils.isNotBlank(cd) && StringUtils.isNotBlank(origin)
                        && cd.trim().equals(origin.trim())) {
                    response.addHeader("Access-Control-Allow-Origin", cd);
                    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                    response.setHeader("Access-Control-Allow-Credentials", "true");
                    break;
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
