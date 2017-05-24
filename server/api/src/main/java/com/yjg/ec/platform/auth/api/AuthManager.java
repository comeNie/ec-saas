package com.yjg.ec.platform.auth.api;

import com.yjg.ec.platform.auth.result.dto.LoginResultUser;

/**
 * Created by zhangyunfei on 12/02/2017.
 */
public interface AuthManager {

	boolean loggedUser(String sessionId, LoginResultUser loginUser);

	boolean extension(String sessionId);

	boolean isLoggedUser(String sessionId);

	LoginResultUser getLoginUser(String sessionId);

	void removeLoginUser(String sessionId);

	boolean updateLoginUser(String sessionId, LoginResultUser loginUser);

	boolean updateUserInfo(String sessionId, Object object);
}
