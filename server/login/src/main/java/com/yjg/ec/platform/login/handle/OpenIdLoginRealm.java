package com.yjg.ec.platform.login.handle;

import com.yjg.ec.platform.auth.dto.LoginUser;

/**
 * Created by zhangyunfei on 19/01/2017.
 */
public interface OpenIdLoginRealm {

    LoginUser loadUserByOpenId(String oauthServerType, String code);
}
