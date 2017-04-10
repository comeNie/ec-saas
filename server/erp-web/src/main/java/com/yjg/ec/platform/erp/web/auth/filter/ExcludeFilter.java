package com.yjg.ec.platform.erp.web.auth.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gus on 9/10/15.
 */
public class ExcludeFilter extends DelegatingFilterProxy {

    private String excludeUrl;

    private PathMatcher pathMatcher = new AntPathMatcher();

    public String getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(String excludeUrl) {
        this.excludeUrl = excludeUrl;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String servletPath = httpRequest.getServletPath();
            if (matchUrl(servletPath)) {
                filterChain.doFilter(request, response);
            } else {
                super.doFilter(request, response, filterChain);
            }
        }
    }

    private boolean matchUrl(String servletPath) {
        if (StringUtils.isBlank(excludeUrl)) {
            return false;
        }
        String[] excludeUrls = excludeUrl.split(",");

        for (String excludeStr : excludeUrls) {
            if (pathMatcher.match(excludeStr, servletPath)) {
                return true;
            }
        }

        return false;
    }
}
