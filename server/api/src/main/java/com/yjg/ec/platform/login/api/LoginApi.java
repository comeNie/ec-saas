package com.yjg.ec.platform.login.api;

import org.springframework.validation.Errors;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.login.param.dto.LoginUserPasswordFreeParamDto;

public interface LoginApi {

	public Result<Boolean> isLoggedUser(String sessionId);

	public Result<String> loginPasswordFree(LoginUserPasswordFreeParamDto loginUserPasswordFreeParamDto, Errors errors);

}
