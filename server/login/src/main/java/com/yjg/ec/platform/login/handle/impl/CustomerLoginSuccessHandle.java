package com.yjg.ec.platform.login.handle.impl;

import com.yjg.ec.platform.auth.dto.LoginUser;
import com.yjg.ec.platform.login.dao.CustomerDao;
import com.yjg.ec.platform.login.domain.CustomerInfo;
import com.yjg.ec.platform.login.handle.LoginSuccessHandle;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangyunfei on 20/01/2017.
 */
@Component
public class CustomerLoginSuccessHandle implements LoginSuccessHandle {

	@Resource
	private CustomerDao customerDao;

	@Override
	public String handle(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		CustomerInfo customerInfo = customerDao.getPatientInfoByLoginName(loginUser.getUsername());
		loginUser.setUserInfo(customerInfo);
		return result;
	}
}
