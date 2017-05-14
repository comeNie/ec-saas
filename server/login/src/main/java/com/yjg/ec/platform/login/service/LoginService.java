package com.yjg.ec.platform.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjg.ec.platform.login.param.dto.LoginUserParamDto;
import com.yjg.ec.platform.login.param.dto.LoginUserPasswordFreeParamDto;
import com.yjg.ec.platform.login.param.dto.OpenIdLoginParamDto;

public interface LoginService {

	public String login(LoginUserParamDto loginUserParamDto, HttpServletRequest request, HttpServletResponse response);

	public boolean logout(HttpServletRequest request, HttpServletResponse response);

	public String openIdLogin(OpenIdLoginParamDto openIdLoginParamDto, HttpServletRequest request,
			HttpServletResponse response);

	public Boolean isLoggedUser(String sessionId);

	public String loginPasswordFree(LoginUserPasswordFreeParamDto loginUserPasswordFreeParamDto);

}
