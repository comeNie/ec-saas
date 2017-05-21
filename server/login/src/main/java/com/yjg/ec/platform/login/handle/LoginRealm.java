package com.yjg.ec.platform.login.handle;

import com.yjg.ec.platform.auth.dto.LoginUser;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
public interface LoginRealm {

	LoginUser loadUserByName(String username);
}
