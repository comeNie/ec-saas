package com.yjg.ec.platform.weixin.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.common.Result;

@RestApi
public interface WeixinImgApi {
	@RequestMapping("/inner/weixin/downloadImg")
	public Result<byte[]> downloadImg(@RequestBody String mediaId);
}
