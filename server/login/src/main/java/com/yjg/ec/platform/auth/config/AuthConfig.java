package com.yjg.ec.platform.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.common.exception.BusinessException;
import com.yjg.ec.platform.login.handle.LoginFailureHandle;
import com.yjg.ec.platform.login.handle.LoginRealm;
import com.yjg.ec.platform.login.handle.LoginSuccessHandle;
import com.yjg.ec.platform.login.handle.LogoutHandle;
import com.yjg.ec.platform.login.handle.OpenIdLoginRealm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyunfei on 19/01/2017.
 */
@Component
public class AuthConfig {

	private Map<String, LoginRealm> loginRealmMap = new HashMap<>();

	private Map<String, LoginSuccessHandle> loginSuccessHandleMap = new HashMap<>();

	private Map<String, LoginFailureHandle> loginFailureHandleMap = new HashMap<>();

	private Map<String, OpenIdLoginRealm> openIdLoginRealmMap = new HashMap<>();

	private Map<String, LogoutHandle> logoutHandleMap = new HashMap<>();

	private Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();

	private List<String> notAuthenRequired = new ArrayList<>();

	public void permit(String... urls) {
		if (urls == null || urls.length <= 0) {
			return;
		}
		for (String url : urls) {
			if (StringUtils.isBlank(url)) {
				continue;
			}
			notAuthenRequired.add(url);
		}
	}

	public void registerLoginRealm(String type, LoginRealm loginRealm) {
		if (StringUtils.isBlank(type) || loginRealm == null) {
			throw new BusinessException("type or loginRealm cannot be null");
		}
		loginRealmMap.put(type, loginRealm);
	}

	public void registerLoginSuccessHandle(String type, LoginSuccessHandle loginSuccessHandle) {
		if (StringUtils.isBlank(type) || loginSuccessHandle == null) {
			throw new BusinessException("type or loginSuccessHandle cannot be null");
		}
		loginSuccessHandleMap.put(type, loginSuccessHandle);
	}

	public void registerLoginFailureHandle(String type, LoginFailureHandle loginFailureHandle) {
		if (StringUtils.isBlank(type) || loginFailureHandle == null) {
			throw new BusinessException("type or loginFailureHandle cannot be null");
		}
		loginFailureHandleMap.put(type, loginFailureHandle);
	}

	public void registerLogoutHandleMap(String type, LogoutHandle logoutHandle) {
		if (StringUtils.isBlank(type) || logoutHandle == null) {
			throw new BusinessException("type or loginSuccessHandle cannot be null");
		}
		logoutHandleMap.put(type, logoutHandle);
	}

	public void registerOpenIdLoginRealm(String type, OpenIdLoginRealm openIdLoginRealm) {
		if (StringUtils.isBlank(type) || openIdLoginRealm == null) {
			throw new BusinessException("type or openIdLoginRealm cannot be null");
		}
		openIdLoginRealmMap.put(type, openIdLoginRealm);
	}

	public void registerPasswordEncoder(String type, PasswordEncoder passwordEncoder) {
		if (StringUtils.isBlank(type) || passwordEncoder == null) {
			throw new BusinessException("type or passwordEncoder cannot be null");
		}
		passwordEncoderMap.put(type, passwordEncoder);
	}

	public LoginRealm getLoginRealm(String type) {
		return loginRealmMap.get(type);
	}

	public LoginSuccessHandle getLoginSuccessHandle(String type) {
		return loginSuccessHandleMap.get(type);
	}

	public LoginFailureHandle getLoginFailureHandle(String type) {
		return loginFailureHandleMap.get(type);
	}

	public LogoutHandle getLogoutHandle(String type) {
		return logoutHandleMap.get(type);
	}

	public OpenIdLoginRealm getOpenIdLoginRealm(String type) {
		return openIdLoginRealmMap.get(type);
	}

	public PasswordEncoder getPasswordEncoder(String type) {
		return passwordEncoderMap.get(type);
	}

}
