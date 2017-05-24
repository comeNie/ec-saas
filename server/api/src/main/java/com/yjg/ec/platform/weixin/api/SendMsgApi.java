package com.yjg.ec.platform.weixin.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

public interface SendMsgApi {
	@RequestMapping("/weixin/SendMsgToPerson")
	public void SendMsgToPerson(JSONObject data);
}
