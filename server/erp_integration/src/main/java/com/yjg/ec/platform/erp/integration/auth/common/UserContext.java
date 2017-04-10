package com.yjg.ec.platform.erp.integration.auth.common;

import org.apache.shiro.SecurityUtils;

import com.yjg.ec.platform.erp.integration.auth.web.ShiroUser;

public class UserContext {

    /**
     * @return ShiroUser
     * @Title getCurrentUser
     * @Description 获取当前登录人信息
     * @author niuqinghua
     * @date： 2013-8-16 上午11:55:16
     */
    public static ShiroUser getCurrentUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * @return String
     * @Title getLoginName
     * @Description 获取当前登录名
     * @author niuqinghua
     * @date： 2013-8-16 下午01:22:44
     */
    public static String getLoginName() {
        ShiroUser user = getCurrentUser();
        return user != null ? user.getAccount() : null;
    }

}
