package com.yjg.ec.platform.wechet.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.util.weixin.HttpClientUtil;

@Service
public class TokenService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenService.class);
	private String access_token;
	private long expiresTime = -1;

	/**
	 * 申请的公众号app id
	 */
	@Value("${weixin.appId}")
	private String APP_ID;
	// private String APP_ID = "wxb5783315640d74f8";

	/**
	 * 申请的公众号安全码
	 */
	@Value("${weixin.appSecret}")
	private String APP_SECRET;
	// private String APP_SECRET = "5ad0d49a2f12a635f469d552458ce842";

	private String WEIXIN_BASE_URL = "https://api.weixin.qq.com/";

	public String getAccess_token(boolean forceRefresh) {
		long curTime = new Date().getTime() / 1000;
		if (forceRefresh || expiresTime < 0 || curTime >= expiresTime) {
			requestAccessToken();
		}
		return access_token;
	}

	private void requestAccessToken() {
		try {
			long curTime = new Date().getTime() / 1000;
			String url = WEIXIN_BASE_URL
					+ "cgi-bin/token?grant_type=client_credential&appid="
					+ APP_ID + "&secret=" + APP_SECRET;

			HttpClientUtil httpClient = new HttpClientUtil();
			String strJson = httpClient.sendGet(true, url);
			JSONObject obj = JSONObject.parseObject(strJson);

			if (obj.containsKey("access_token")
					&& obj.containsKey("expires_in")) {
				expiresTime = curTime + obj.getLong("expires_in");
				access_token = obj.getString("access_token");
			} else {
				LOGGER.error("调用微信发生错误：获取access_token错误");
			}
		} catch (Exception e) {
			LOGGER.error("调用微信发生异常：获取access_token异常");
		}
	}

}
