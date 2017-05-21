package com.yjg.ec.platform.login.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjg.ec.platform.auth.dto.LoginUser;

/**
 * Created by mengzipeng on 24/03/2017.
 */
public interface LoginFailureHandle {

	String handle(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response);
}
