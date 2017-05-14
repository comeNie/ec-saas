package com.yjg.ec.platform.auth.api;

import com.yjg.ec.platform.auth.dto.LoginUser;

/**
 * Created by zhangyunfei on 12/02/2017.
 */
public interface AuthManager {

	boolean loggedUser(String sessionId, LoginUser loginUser);

	boolean extension(String sessionId);

	boolean isLoggedUser(String sessionId);

	LoginUser getLoginUser(String sessionId);

	void removeLoginUser(String sessionId);

	boolean updateLoginUser(String sessionId, LoginUser loginUser);

	boolean updateUserInfo(String sessionId, Object object);
}
