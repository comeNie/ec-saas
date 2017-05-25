package com.yjg.ec.platform.wechat.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.wechat.util.WeixinHttpUtil;
import com.yjg.ec.platform.weixin.api.WeixinImgApi;

@RestController
public class ImgService implements WeixinImgApi {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ImgService.class);
	private static final String download_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	@Resource
	private TokenService tokenService;

	@SuppressWarnings("unchecked")
	@Override
	public Result<byte[]> downloadImg(@RequestBody String mediaId) {
		boolean isRefreshToken = false;
		String accessToken = "";
		if (mediaId == null) {
			LOGGER.error("Weixin:download image file fail,mediaId is null.");
			return Result.buildBusinessErrorResult("微信图片下载失败，media id是空");
		}
		for (int i = 0; i < 2; i++) {
			accessToken = tokenService.getAccess_token(isRefreshToken);

			String url = download_url.replace("ACCESS_TOKEN", accessToken)
					.replace("MEDIA_ID", mediaId);
			byte[] data = WeixinHttpUtil.httpRequest_byte(url, "GET", null);
			if (data.length < 200) {
				String tmp = new String(data);
				if (tmp.startsWith("{") && tmp.endsWith("}")) {
					JSONObject obj = JSONObject.parseObject(new String(data));

					if (obj.getString("errcode").equals("40001")
							|| obj.getString("errcode").equals("40014")
							|| obj.getString("errcode").equals("42001")) {
						// access token 无效，刷新token，重新发送一次
						isRefreshToken = true;
						continue;
					} else if (obj.getString("errcode").equals("40007")) {
						// 图片下载失败
						LOGGER.error("Weixin:download image file fail,mediaId is not exist.");
						return Result
								.buildBusinessErrorResult("微信图片下载失败，media id不存在");
					} else {
						// 图片下载失败
						LOGGER.error("Weixin:download image file fail:" + tmp);
						return Result.buildBusinessErrorResult("微信图片下载失败。");
					}
				}
			}

			return Result.buildSuccessResult(data);

		}
		return Result.buildBusinessErrorResult("微信图片下载失败!");
	}
	/*
	 * private String saveFile(byte []data) { String fileName = ""; String
	 * filePath = "/Users/lzy/123.jpg";
	 * 
	 * UUID uuid = UUID.randomUUID(); fileName= uuid.toString().replace("-","");
	 * 
	 * try { FileOutputStream os = new FileOutputStream(filePath);
	 * os.write(data); os.close(); return filePath; } catch (Exception e) {
	 * LOGGER.error("Weixin:saving file to server fail," + e.getMessage()); }
	 * return null; }
	 */
}
