package com.yjg.ec.platform.login.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yjg.ec.platform.auth.annotation.NotAuthenRequired;
import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.exception.ParamException;
import com.yjg.ec.platform.login.param.dto.LoginUserParamDto;
import com.yjg.ec.platform.login.param.dto.LoginUserPasswordFreeParamDto;
import com.yjg.ec.platform.login.param.dto.OpenIdLoginParamDto;
import com.yjg.ec.platform.login.service.LoginService;

@RestController
public class LoginApiImpl implements LoginApi {

	@Resource
	private LoginService loginService;

	@NotAuthenRequired
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<String> login(@Valid LoginUserParamDto loginUserParamDto, Errors errors, HttpServletRequest request,
			HttpServletResponse response) {
		if (errors.hasErrors()) {
			throw new ParamException(errors);
		}
		// 100:登录错误次数大于等于限制次数
		// 101:此设备不受信
		String sessionId = loginService.login(loginUserParamDto, request, response);
		return Result.buildSuccessResult("", sessionId);
	}

	@NotAuthenRequired
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Result<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = loginService.logout(request, response);
		return Result.buildSuccessResult(flag);
	}

	@NotAuthenRequired
	@RequestMapping(value = "/login/wechat", method = RequestMethod.POST)
	public Result<String> wechatLogin(OpenIdLoginParamDto openIdLoginParamDto, HttpServletRequest request,
			HttpServletResponse response) {
		String sessionId = "";
		return Result.buildSuccessResult("", sessionId);
	}

	@Override
	public Result<Boolean> isLoggedUser(@PathVariable String sessionId) {
		Boolean flag = true;
		return Result.buildSuccessResult(flag);
	}

	@Override
	@NotAuthenRequired
	public Result<String> loginPasswordFree(
			@RequestBody @Valid LoginUserPasswordFreeParamDto loginUserPasswordFreeParamDto, Errors errors) {
		if (errors.hasErrors()) {
			throw new ParamException(errors);
		}
		String sessionId = "";
		return Result.buildSuccessResult("", sessionId);
	}

}
