package com.yjg.ec.platform.erp.integration.auth.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yjg.ec.platform.erp.integration.auth.util.AuthUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthUserLogoutFilter extends LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthUserLogoutFilter.class);

    private String cookieDomain;
    private String cookieName;
    private String cookiePath;

    /**
     * 覆盖父类的preHandle方法
     * 第一步清空passport设置的name为ceshi3的cookie;
     * 第二步获取Subject实例和redirectUrl;
     * 第三步调用Subject的logout方法,执行相关的退出操作;
     * 第四步调用issueRedirect设置用户退出之后显示页面的URL
     * 第五步返回false,注意只能返回false.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        AuthUtils.deleteCookie((HttpServletRequest) request, (HttpServletResponse) response, cookieDomain, cookiePath, cookieName);
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        issueRedirect(request, response, redirectUrl);
        return false;
    }

    /**
     * @return cookieDomain
     * @Description get cookieDomain
     */
    public String getCookieDomain() {
        return cookieDomain;
    }

    /**
     * @param cookieDomain
     * @Description set cookieDomain
     */
    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    /**
     * @return cookieName
     * @Description get cookieName
     */
    public String getCookieName() {
        return cookieName;
    }

    /**
     * @param cookieName
     * @Description set cookieName
     */
    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    /**
     * @return cookiePath
     * @Description get cookiePath
     */
    public String getCookiePath() {
        return cookiePath;
    }

    /**
     * @param cookiePath
     * @Description set cookiePath
     */
    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

}
