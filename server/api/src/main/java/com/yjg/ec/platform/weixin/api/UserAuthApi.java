package com.yjg.ec.platform.weixin.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

public interface UserAuthApi {
	@RequestMapping("/weixin/auth")
	public String getUnionId(HttpServletRequest request,
			HttpServletResponse response);
}
