package com.yjg.ec.platform.wechat.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.util.weixin.HttpClientUtil;

/*
 {
 "touser":"OPENID",
 "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
 "url":"http://weixin.qq.com/download",            
 "data":{
 "first": {
 "value":"恭喜你购买成功！",
 "color":"#173177"
 },
 "keynote1":{
 "value":"巧克力",
 "color":"#173177"
 },
 "keynote2": {
 "value":"39.8元",
 "color":"#173177"
 },
 "remark":{
 "value":"欢迎再次购买！",
 "color":"#173177"
 }
 }
 }
 */

@RestController
public class SendMsg {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenService.class);
	private String WEIXIN_BASE_URL = "https://api.weixin.qq.com/";

	@Resource
	private TokenService tokenService;

	/**
	 * 向单个用户发送业务通知
	 */
	public void SendMsgToPerson(JSONObject data) {
		String retJson = "";
		boolean isRefreshToken = false;
		try {
			for (int i = 0; i < 2; i++) {
				String url = WEIXIN_BASE_URL
						+ "cgi-bin/message/template/send?access_token="
						+ tokenService.getAccess_token(isRefreshToken);
				HttpClientUtil httpClient = new HttpClientUtil();
				retJson = httpClient.sendPost(true, url, data);
				if (retJson != null) {
					JSONObject obj = JSONObject.parseObject(retJson);
					if (obj.getString("errcode").equals("0")) {
						// 发送成功
						LOGGER.info("调用微信，发送业务通知成功。");
						return;
					} else if (obj.getString("errcode").equals("40014")
							|| obj.getString("errcode").equals("40001")
							|| obj.getString("errcode").equals("42001")) {
						// access token 无效，刷新token，重新发送一次
						isRefreshToken = true;
						continue;
					} else {
						// 其它发送错误，直接记录日志。不再重新发送。
						LOGGER.error("调用微信，发送业务通知失败。返回内容：" + retJson);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("调用微信，发送业务通知发生异常。内容：" + e.getMessage());
			return;
		}
	}

}
