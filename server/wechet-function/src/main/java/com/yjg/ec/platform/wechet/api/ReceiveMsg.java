package com.yjg.ec.platform.wechet.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.common.util.weixin.SignUtil;

@Controller
@RequestMapping("/weixin")
public class ReceiveMsg {
	/**
	 * 此token为申请公众号时设置的token,需要保持一致
	 */
	private static final String TOKEN = "123456";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReceiveMsg.class);

	/**
	 * 用于微信申请时的url验证
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/receivemsg")
	public void bindServer(HttpServletRequest request,
			HttpServletResponse response) {
		if (isLegal(request)) {
			try {
				PrintWriter pw = response.getWriter();
				pw.write(request.getParameter("echostr"));
				pw.flush();
				pw.close();
			} catch (Exception e) {
				LOGGER.error("绑定服务器异常", e);
			}
		}
	}

	protected boolean isLegal(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		return SignUtil.checkSignature(TOKEN, signature, timestamp, nonce);
	}
}
