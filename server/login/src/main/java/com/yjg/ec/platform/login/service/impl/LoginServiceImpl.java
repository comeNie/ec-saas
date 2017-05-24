package com.yjg.ec.platform.login.service.impl;

import java.util.UUID;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjg.ec.platform.auth.api.AuthManager;
import com.yjg.ec.platform.auth.result.dto.LoginResultUser;
import com.yjg.ec.platform.customer.param.dto.CustomerUserParamDto;
import com.yjg.ec.platform.login.dao.CustomerDao;
import com.yjg.ec.platform.login.dao.CustomerUserDao;
import com.yjg.ec.platform.login.domain.CustomerInfo;
import com.yjg.ec.platform.login.param.dto.LoginUserParamDto;
import com.yjg.ec.platform.login.param.dto.OpenIdLoginParamDto;
import com.yjg.ec.platform.login.result.dto.CustomerResultDto;
import com.yjg.ec.platform.login.service.LoginService;
import com.yjg.ec.platform.weixin.api.WeixinUserinfoApi;
import com.yjg.ec.platform.weixin.param.dto.WeixinUserinfoDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangyunfei on 18/01/2017.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource
	private CustomerUserDao customerUserDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private WeixinUserinfoApi weixinUserinfoApi;

	@Resource
	private AuthManager authManager;

	@Autowired
	private Mapper mapper;

	@Override
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

	@Override
	public String openIdLogin(OpenIdLoginParamDto openIdLoginParamDto, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info("code==============" + openIdLoginParamDto.getCode());

		LoginResultUser loginUser;
		try {
			loginUser = loadUserByOpenId(openIdLoginParamDto.getOauthServerType(), openIdLoginParamDto.getCode());
		} catch (Exception e) {
			throw new RuntimeException("登陆失败！", e);
		}

		if (loginUser == null) {
			throw new RuntimeException("登陆失败！");
		}

		try {
			CustomerInfo customerInfo = customerDao.getCustomerInfoByLoginName(loginUser.getUsername());
			CustomerResultDto customerResultDto = mapper.map(customerInfo, CustomerResultDto.class);
			loginUser.setUserInfo(customerResultDto);
		} catch (Exception e) {
			throw new RuntimeException("登陆失败！", e);
		}
		HttpSession session = request.getSession();
		authManager.loggedUser(session.getId(), loginUser);
		return session.getId();
	}

	private LoginResultUser loadUserByOpenId(String oauthServerType, String code) {
		// call wechat api get openid
		WeixinUserinfoDto dto = weixinUserinfoApi.getUserInfo(code);

		LoginResultUser loginResultUser = customerUserDao.getCustomerLoginByWechatOpenId(dto.getOpenId());
		if (loginResultUser == null) {
			CustomerUserParamDto customerUserParamDto = new CustomerUserParamDto();
			customerUserParamDto.setUsername(buildUserName());
			customerUserParamDto.setWechatOpenId(dto.getOpenId());
			customerUserParamDto.setWechatUnionId(dto.getUnionId());
			long row = customerUserDao.addCustomer(customerUserParamDto);
			if (row > 0) {
				if (customerDao.initCustomer(customerUserParamDto.getId()) > 0) {
					loginResultUser = customerUserDao.getCustomerLoginByWechatOpenId(dto.getOpenId());
				}
			}
		}

		return loginResultUser;
	}

	public static String buildUserName() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.replace("-", "");
	}

	@Override
	public String login(LoginUserParamDto loginUserParamDto, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
