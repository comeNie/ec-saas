package com.yjg.ec.platform.weixin.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.weixin.param.dto.WeixinUserinfoDto;

@RestApi
public interface WeixinUserinfoApi {
	@RequestMapping("/inner/weixin/getUserInfo")
	public WeixinUserinfoDto getUserInfo(@RequestBody String code);
}
