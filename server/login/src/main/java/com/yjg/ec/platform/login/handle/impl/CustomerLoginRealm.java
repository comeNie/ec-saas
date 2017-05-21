package com.yjg.ec.platform.login.handle.impl;

import org.springframework.stereotype.Component;

import com.yjg.ec.platform.auth.dto.LoginUser;
import com.yjg.ec.platform.login.dao.CustomerUserDao;
import com.yjg.ec.platform.login.handle.LoginRealm;

import javax.annotation.Resource;

/**
 * Created by zhangyunfei on 19/01/2017.
 */
@Component
public class CustomerLoginRealm implements LoginRealm {

	@Resource
	private CustomerUserDao customerUserDao;

	@Override
	public LoginUser loadUserByName(String username) {
		LoginUser loginUser = customerUserDao.getPatientLogin(username);
		return loginUser;
	}
}
