package com.yjg.ec.platform.weixin.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ReceiveMsgApi {
	@RequestMapping("/weixin/bindServer")
	public void bindServer(HttpServletRequest request, HttpServletResponse response);
}
