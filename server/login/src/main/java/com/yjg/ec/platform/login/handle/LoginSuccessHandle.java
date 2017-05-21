package com.yjg.ec.platform.login.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjg.ec.platform.auth.dto.LoginUser;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
public interface LoginSuccessHandle {

	String handle(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response);
}
