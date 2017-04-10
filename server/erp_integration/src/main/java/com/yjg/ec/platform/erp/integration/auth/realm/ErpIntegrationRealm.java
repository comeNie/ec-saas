package com.yjg.ec.platform.erp.integration.auth.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yjg.ec.platform.erp.integration.auth.common.AuthToken;
import com.yjg.ec.platform.erp.integration.auth.service.AuthService;
import com.yjg.ec.platform.erp.integration.auth.web.ShiroUser;

import javax.annotation.Resource;

public class ErpIntegrationRealm extends AuthorizingRealm {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    AuthService authService;

    public ErpIntegrationRealm() {
        setAuthenticationTokenClass(AuthToken.class);
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }

        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        // String userId = (String)
        // principalCollection.fromRealm(getName()).iterator().next();
        Integer userId = shiroUser.getId();
        if (userId == null) {
            return null;
        }
        // 添加角色及权限信息
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        try {
            sazi.addStringPermissions(authService.getAuthCodes(userId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sazi;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        if(authcToken == null){
            logger.error("authcToken为null.");
            throw new IncorrectCredentialsException();
        }

        if(authcToken.getPrincipal() == null){
            logger.error("authcToken principal为null.");
            throw new IncorrectCredentialsException();
        }

        if(authcToken.getCredentials() == null){
            logger.error("authcToken credentials为null.");
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(authcToken.getPrincipal(), authcToken.getCredentials(), getName());
    }

}
