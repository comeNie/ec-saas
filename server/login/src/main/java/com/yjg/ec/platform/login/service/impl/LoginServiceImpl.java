package com.yjg.ec.platform.login.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yjg.ec.platform.auth.api.AuthManager;
import com.yjg.ec.platform.common.util.CookieUtil;
import com.yjg.ec.platform.common.util.RedisUtil;

import javax.annotation.Resource;
import javax.security.auth.message.config.AuthConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
@Service
public class LoginServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource
	private AuthConfig authConfig;

	@Resource
	private AuthManager authManager;

	@Resource
	private CookieUtil cookieUtil;

	@Resource
	private RedisUtil redisUtil;

	@Resource
	private SecurityApi securityApi;

	@Autowired
	private Mapper mapper;

	public String login(LoginUserDto loginUserDto, HttpServletRequest request, HttpServletResponse response) {
		// 校验图片验证码是否正确
		Result<Boolean> validateCaptchaCodeResult = null;
		if (StringUtils.isNotBlank(loginUserDto.getCaptchaCode())) {
			Cookie cookie = cookieUtil.getCookieByName(request, SecurityConstants.IMG_CAPTCHA_COOKIE_NAME);
			if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
				LogUtils.buriedLogByName(
						new LogInfo(loginUserDto.getUsername(), UserTypeEnum.getTypeBydesc(loginUserDto.getType()),
								BuriedPointConstants.BURIED_POINT_MODEL_LOGIN, "登录失败，图片验证码已过期"));
				throw new BusinessException("图片验证码已过期，请重新获取");
			}
			validateCaptchaCodeResult = securityApi.validateCaptchaCode(loginUserDto.getCaptchaCode(),
					cookie.getValue());
			if (validateCaptchaCodeResult == null || validateCaptchaCodeResult.getCode() != 200
					|| validateCaptchaCodeResult.getResultData() == null) {
				throw new BusinessException(
						validateCaptchaCodeResult == null ? "校验图片验证码返回结果为空" : validateCaptchaCodeResult.getMessage());
			}
			if (!validateCaptchaCodeResult.getResultData()) {
				LogUtils.buriedLogByName(
						new LogInfo(loginUserDto.getUsername(), UserTypeEnum.getTypeBydesc(loginUserDto.getType()),
								BuriedPointConstants.BURIED_POINT_MODEL_LOGIN, "登录失败，图片验证码输入有误"));
				throw new BusinessException("图片验证码输入有误，请重新输入");
			}
		}

		LoginRealm loginRealm = authConfig.getLoginRealm(loginUserDto.getType());
		PasswordEncoder passwordEncoder = authConfig.getPasswordEncoder(loginUserDto.getType());
		if (loginRealm == null) {
			throw new RuntimeException("no suitable realm for this type");
		}
		LoginUser loginUser;
		try {
			loginUser = loginRealm.loadUserByName(loginUserDto.getUsername());
		} catch (Exception e) {
			throw new RuntimeException("登陆失败！", e);
		}

		if (loginUser != null) {
			if (passwordEncoder.matches(loginUserDto.getPassword(), loginUser.getPassword())) {
				LoginSuccessHandle loginSuccessHandle = authConfig.getLoginSuccessHandle(loginUserDto.getType());
				if (loginSuccessHandle != null) {
					try {
						DoctorLoginDeviceInfoDto deviceInfoDto = mapper.map(loginUserDto,
								DoctorLoginDeviceInfoDto.class);
						String result = loginSuccessHandle.handle(loginUser, deviceInfoDto, request, response);
						if (StringUtils.isNoneBlank(result))
							return result;
					} catch (Exception e) {
						throw new RuntimeException("登陆失败！", e);
					}
				}

				HttpSession session = request.getSession();
				loggedUser(session, loginUser);
				cookieUtil.addCookie(request, response, "sid", session.getId(), 0);
				LogUtils.buriedLogByName(
						new LogInfo(loginUserDto.getUsername(), UserTypeEnum.getTypeBydesc(loginUserDto.getType()),
								BuriedPointConstants.BURIED_POINT_MODEL_LOGIN, "登录成功"));
				return session.getId();
			} else {
				if (validateCaptchaCodeResult != null && validateCaptchaCodeResult.getResultData()) {// 用户名密码错误时，图片验证码通过时说明是用户名密码输入错误
					LogUtils.buriedLogByName(
							new LogInfo(loginUserDto.getUsername(), UserTypeEnum.getTypeBydesc(loginUserDto.getType()),
									BuriedPointConstants.BURIED_POINT_MODEL_LOGIN, "登录失败，用户名或密码错误"));
					throw new BusinessException("用户名或密码错误，请重新输入");
				}
				LoginFailureHandle loginFailureHandle = authConfig.getLoginFailureHandle(loginUserDto.getType());
				if (loginFailureHandle != null) {
					try {
						String loginFailResult = loginFailureHandle.handle(loginUser, request, response);
						if (StringUtils.isNotBlank(loginFailResult))
							return loginFailResult;
					} catch (Exception e) {
						throw new RuntimeException("登陆失败！", e);
					}
				}
				throw new BusinessException("用户名或密码错误！");
			}
		}
		LogUtils.buriedLogByName(
				new LogInfo(loginUserDto.getUsername(), UserTypeEnum.getTypeBydesc(loginUserDto.getType()),
						BuriedPointConstants.BURIED_POINT_MODEL_LOGIN, "登录失败，用户名不存在"));
		throw new BusinessException("用户名不存在，请重试！");
	}

	public String loginPasswordFree(LoginUserPasswordFreeDto loginUserPasswordFreeDto) {
		LoginRealm loginRealm = authConfig.getLoginRealm(loginUserPasswordFreeDto.getType());
		if (loginRealm == null) {
			throw new RuntimeException("no suitable realm for this type");
		}
		LoginUser loginUser;
		try {
			loginUser = loginRealm.loadUserByName(loginUserPasswordFreeDto.getUsername());
		} catch (Exception e) {
			throw new RuntimeException("登陆失败！", e);
		}

		if (loginUser != null) {
			LoginSuccessHandle loginSuccessHandle = authConfig
					.getLoginSuccessHandle(loginUserPasswordFreeDto.getType());
			if (loginSuccessHandle != null) {
				try {
					loginSuccessHandle.handle(loginUser, null, null, null);
				} catch (Exception e) {
					throw new BusinessException("免密登陆失败！");
				}
			}
			loggerUserPasswordFree(loginUserPasswordFreeDto.getSessionId(), loginUser);
			return loginUserPasswordFreeDto.getSessionId();
		}
		throw new BusinessException("用户名不存在，请重试！");
	}

	public String openIdLogin(OpenIdLoginDto openIdLoginDto, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("code==============" + openIdLoginDto.getCode());
		OpenIdLoginRealm openIdLoginRealm = authConfig.getOpenIdLoginRealm(openIdLoginDto.getType());

		if (openIdLoginRealm == null) {
			throw new RuntimeException("no suitable realm for this type");
		}
		LoginUser loginUser;
		try {
			loginUser = openIdLoginRealm.loadUserByOpenId(openIdLoginDto.getOauthServerType(),
					openIdLoginDto.getCode());
		} catch (Exception e) {
			throw new RuntimeException("登陆失败！", e);
		}

		if (loginUser == null) {
			throw new RuntimeException("登陆失败！");
		}

		LoginSuccessHandle loginSuccessHandle = authConfig.getLoginSuccessHandle(openIdLoginDto.getType());
		if (loginSuccessHandle != null) {
			try {
				loginSuccessHandle.handle(loginUser, null, request, response);
			} catch (Exception e) {
				throw new RuntimeException("登陆失败！", e);
			}
		}
		HttpSession session = request.getSession();
		loggedUser(session, loginUser);
		return session.getId();
	}

	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		try {
			HttpSession session = request.getSession();
			LoginUser loginUser = authManager.getLoginUser(session.getId());
			if (loginUser != null) {
				LogoutHandle logoutHandle = authConfig.getLogoutHandle(loginUser.getType());

				if (logoutHandle != null) {
					logoutHandle.handle(loginUser, request, response);
				}
				authManager.removeLoginUser(session.getId());
			}
		} catch (Exception e) {
			LOGGER.error("delete login info from redis fail or logouthandle execute fail", e);
		}

		return true;
	}

	private void loggedUser(HttpSession session, LoginUser loginUser) {
		authManager.loggedUser(session.getId(), loginUser);
	}

	private void loggerUserPasswordFree(String sessionId, LoginUser loginUser) {
		authManager.loggedUser(sessionId, loginUser);
	}

	public boolean isLoggedUser(String sessionId) {
		return authManager.isLoggedUser(sessionId);
	}

}
