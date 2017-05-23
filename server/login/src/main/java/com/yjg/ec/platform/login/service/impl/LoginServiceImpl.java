package com.yjg.ec.platform.login.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nrb.maledisease.auth.handle.LoginFailureHandle;
import com.nrb.maledisease.auth.handle.LoginSuccessHandle;
import com.nrb.maledisease.auth.type.UserTypeEnum;
import com.nrb.maledisease.common.constant.BuriedPointConstants;
import com.nrb.maledisease.common.domain.LogInfo;
import com.nrb.maledisease.common.exception.BusinessException;
import com.nrb.maledisease.common.util.LogUtils;
import com.nrb.maledisease.security.dto.DoctorLoginDeviceInfoDto;
import com.yjg.ec.platform.auth.dto.LoginUser;
import com.yjg.ec.platform.login.dao.CustomerDao;
import com.yjg.ec.platform.login.dao.CustomerUserDao;
import com.yjg.ec.platform.login.param.dto.LoginUserParamDto;
import com.yjg.ec.platform.login.service.LoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginServiceImpl.class);

	@Resource
	private CustomerUserDao customerUserDao;

	@Resource
	private CustomerDao customerDao;

	@Autowired
	private Mapper mapper;

	public String login(LoginUserParamDto loginUserParamDto,
			HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	@Override
	public boolean logout(HttpServletRequest request,
			HttpServletResponse response) {
		return false;
	}

}
