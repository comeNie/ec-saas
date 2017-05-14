package com.yjg.ec.platform.security.api;

import com.yjg.ec.platform.common.Result;

public interface SecurityApi {

	public Result<Boolean> validateCaptchaCode(String captchaCode, String captchaCodeRedisKey);

}
