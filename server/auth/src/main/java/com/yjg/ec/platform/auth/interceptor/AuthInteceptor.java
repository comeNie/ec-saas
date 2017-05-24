package com.yjg.ec.platform.auth.interceptor;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yjg.ec.platform.auth.annotation.AuthCode;
import com.yjg.ec.platform.auth.annotation.NotAuthenRequired;
import com.yjg.ec.platform.auth.api.AuthManager;
import com.yjg.ec.platform.auth.common.UserUtil;
import com.yjg.ec.platform.auth.result.dto.LoginResultUser;
import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.util.ResponseJsonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

/**
 * Created by zhangyunfei on 19/01/2017.
 */
@Component
public class AuthInteceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInteceptor.class);

	@Resource
	private AuthManager authManager;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AuthCode authCode;

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			NotAuthenRequired notAuthenRequired = method.getAnnotation(NotAuthenRequired.class);
			authCode = method.getAnnotation(AuthCode.class);
			if (notAuthenRequired != null) {
				return true;
			}
		} else {
			return true;
		}
		LoginResultUser loginUser = null;

		try {
			loginUser = authManager.getLoginUser(request.getSession().getId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (loginUser == null) {
			Result result = Result.buildAuthExceptionResult("请您重新登录！");
			ResponseJsonUtil.responseJson(response, result);
			return false;
		}

		if (authCode != null) {
			String[] codeList = authCode.value();

			if (codeList != null && codeList.length > 0) {
				if (CollectionUtils.isEmpty(loginUser.getAuthList())) {
					Result result = Result.buildAuthExceptionResult("没有访问权限！");
					ResponseJsonUtil.responseJson(response, result);
					return false;
				}
				for (String code : codeList) {
					if (loginUser.getAuthList().contains(code)) {
						authManager.extension(request.getSession().getId());
						UserUtil.setCurrentLoginUser(loginUser);
						return true;
					}
				}
				return false;
			}

		}
		UserUtil.setCurrentLoginUser(loginUser);
		return super.preHandle(request, response, handler);
	}
}
