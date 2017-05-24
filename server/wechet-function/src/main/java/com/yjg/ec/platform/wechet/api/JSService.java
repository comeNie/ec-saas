package com.yjg.ec.platform.wechet.api;

import java.util.Date;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.util.weixin.HttpClientUtil;
import com.yjg.ec.platform.common.util.weixin.RandomUtil;
import com.yjg.ec.platform.common.util.weixin.SignUtil;
import com.yjg.ec.platform.weixin.api.WeixinJsApi;

@RestController
public class JSService implements WeixinJsApi {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenService.class);

	@Resource
	private TokenService tokenService;

	@Value("${weixin.appId}")
	private String appId;

	private String baseUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

	private String getJSTicket() {
		boolean isRefreshToken = false;
		String url;
		for (int i = 0; i < 2; i++) {
			url = baseUrl + tokenService.getAccess_token(isRefreshToken);
			HttpClientUtil httpClient = new HttpClientUtil();
			String retJson = httpClient.sendGet(true, url);

			if (retJson != null) {
				JSONObject obj = JSONObject.parseObject(retJson);
				if (obj.getString("errcode").equals("0")) {
					// 发送成功
					return obj.getString("ticket");
				} else if (obj.getString("errcode").equals("40014")
						|| obj.getString("errcode").equals("42001")) {
					// access token 无效，刷新token，重新发送一次
					isRefreshToken = true;
					continue;
				} else {
					// 其它发送错误，直接记录日志。不再重新发送。
					LOGGER.error("调用微信，获取jssdk临时票据失败。返回内容：" + retJson);
					break;
				}
			}
		}
		return "";
	}

	private String subUrl(String url) {
		int index = url.indexOf("#");
		if (index < 0)
			return url;
		else
			return url.substring(0, index);
	}

	private String mapToString(TreeMap<String, String> map) {
		String ret = "";
		for (String key : map.keySet()) {
			ret += key + "=" + map.get(key) + "&";
		}
		ret = ret.substring(0, ret.length() - 1);
		return ret;
	}

	public Result<JSONObject> getJSConfigParams(String url) {
		String _url = subUrl(url);

		String timestamp = String.valueOf(new Date().getTime() / 1000);
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("noncestr", RandomUtil.getRandomStringByLength(16));
		map.put("jsapi_ticket", getJSTicket());
		map.put("timestamp", timestamp);
		map.put("url", _url);
		String sign = SignUtil.getSHA1(mapToString(map));
		map.put("sign", sign);
		map.put("appId", appId);

		JSONObject obj = JSONObject.parseObject(JSON.toJSONString(map));

		return Result.buildSuccessResult(obj);
	}
}
