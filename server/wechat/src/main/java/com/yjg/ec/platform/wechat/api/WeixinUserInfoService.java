package com.yjg.ec.platform.wechat.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.util.weixin.HttpClientUtil;
import com.yjg.ec.platform.weixin.api.WeixinUserinfoApi;
import com.yjg.ec.platform.weixin.param.dto.WeixinUserinfoDto;

@RestController
public class WeixinUserInfoService implements WeixinUserinfoApi {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeixinUserInfoService.class);
	/**
	 * 申请的公众号app id
	 */
	@Value("${weixin.appId}")
	private String APP_ID;

	/**
	 * 申请的公众号安全码
	 */
	@Value("${weixin.appSecret}")
	private String APP_SECRET;

	private String WEIXIN_BASE_URL = "https://api.weixin.qq.com/";

	public WeixinUserinfoDto getUserInfo(@RequestBody String code) {
		WeixinUserinfoDto dto = new WeixinUserinfoDto();

		// 获取openid 和 access_token
		String url = WEIXIN_BASE_URL + "sns/oauth2/access_token?appid="
				+ APP_ID + "&secret=" + APP_SECRET + "&code=" + code
				+ "&grant_type=authorization_code";
		HttpClientUtil httpClient = new HttpClientUtil();

		String json = httpClient.sendGet(true, url);
		if (!StringUtils.isBlank(json)) {
			JSONObject obj = (JSONObject) JSON.parse(json);
			if (obj.containsKey("access_token") && obj.containsKey("openid")) {
				String openId = obj.getString("openid");
				String accessToken = obj.getString("access_token");
				url = WEIXIN_BASE_URL + "sns/userinfo?access_token="
						+ accessToken + "&openid=" + openId + "&lang=zh_CN";
				json = httpClient.sendGet(true, url);
				if (!StringUtils.isBlank(json)) {
					obj = (JSONObject) JSON.parse(json);
					if (obj.containsKey("unionid")) {
						String unionId = obj.getString("unionid");
						dto.setOpenId(openId);
						dto.setUnionId(unionId);
					} else {
						dto.setOpenId(openId);
						dto.setUnionId("");

						// 临时设置，正式环境开发平台设置后，需要获取
						// LOGGER.error("微信返回值不包含unionId");
						// throw new NullPointerException("微信返回值不包含unionId");
					}
				}
			} else {
				LOGGER.error("微信返回值不包含openid或accessToken");
				throw new NullPointerException("微信返回值不包含openid或accessToken");
			}
		} else {
			LOGGER.error("获取微信openid为空");
			throw new NullPointerException("获取微信openid为空");
		}

		return dto;
	}
}
