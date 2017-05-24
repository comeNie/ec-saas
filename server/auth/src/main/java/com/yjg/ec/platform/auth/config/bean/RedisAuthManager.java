package com.yjg.ec.platform.auth.config.bean;

import com.yjg.ec.platform.auth.api.AuthManager;
import com.yjg.ec.platform.auth.config.AuthManagerConfig;
import com.yjg.ec.platform.auth.result.dto.LoginResultUser;
import com.yjg.ec.platform.common.exception.BusinessException;
import com.yjg.ec.platform.common.util.RedisUtil;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * Created by zhangyunfei on 12/02/2017.
 */
public class RedisAuthManager implements AuthManager {

	@Resource
	private AuthManagerConfig authManagerConfig;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public boolean updateLoginUser(String sessionId, LoginResultUser loginUser) {
		if (StringUtils.isBlank(sessionId) || loginUser == null) {
			throw new BusinessException("Param error!");
		}
		Object object = redisUtil.get(authManagerConfig.getPrefix() + sessionId);

		if (object != null && !(object instanceof LoginResultUser)) {
			throw new BusinessException("invalid login user");
		}
		return redisUtil.set(authManagerConfig.getPrefix() + sessionId, loginUser);
	}

	@Override
	public boolean updateUserInfo(String sessionId, Object userInfo) {
		if (StringUtils.isBlank(sessionId) || userInfo == null) {
			throw new BusinessException("Param error!");
		}
		Object object = redisUtil.get(authManagerConfig.getPrefix() + sessionId);

		if (object != null && !(object instanceof LoginResultUser)) {
			throw new BusinessException("invalid login user");
		}
		LoginResultUser loginUser = (LoginResultUser) object;
		loginUser.setUserInfo(userInfo);
		return redisUtil.set(authManagerConfig.getPrefix() + sessionId, loginUser);
	}

	@Override
	public boolean loggedUser(String sessionId, LoginResultUser loginUser) {
		return redisUtil.set(authManagerConfig.getPrefix() + sessionId, loginUser, authManagerConfig.getExpireTime());
	}

	@Override
	public boolean extension(String sessionId) {
		if (StringUtils.isBlank(sessionId)) {
			throw new BusinessException("invalid request");
		}
		return redisUtil.expire(authManagerConfig.getPrefix() + sessionId, authManagerConfig.getExpireTime());
	}

	@Override
	public boolean isLoggedUser(String sessionId) {
		Object object = redisUtil.get(authManagerConfig.getPrefix() + sessionId);
		return object != null && object instanceof LoginResultUser;
	}

	@Override
	public LoginResultUser getLoginUser(String sessionId) {
		if (StringUtils.isBlank(sessionId)) {
			throw new BusinessException("获取登录用户信息失败");
		}
		Object object = redisUtil.get(authManagerConfig.getPrefix() + sessionId);

		return (LoginResultUser) object;
	}

	@Override
	public void removeLoginUser(String sessionId) {
		redisUtil.remove(authManagerConfig.getPrefix() + sessionId);
	}

}
