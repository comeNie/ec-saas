package com.yjg.ec.platform.weixin.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.Result;

public interface WeixinJsApi {
	@RequestMapping("/weixin/jsconfig")
	public Result<JSONObject> getJSConfigParams(String url);
}
