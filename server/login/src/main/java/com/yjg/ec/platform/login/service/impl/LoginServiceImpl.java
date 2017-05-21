package com.yjg.ec.platform.login.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjg.ec.platform.login.param.dto.LoginUserParamDto;
import com.yjg.ec.platform.login.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private Mapper mapper;

	public String login(LoginUserParamDto loginUserParamDto, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

}
