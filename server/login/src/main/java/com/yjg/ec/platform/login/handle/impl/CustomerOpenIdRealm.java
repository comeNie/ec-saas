package com.yjg.ec.platform.login.handle.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.login.dao.CustomerDao;
import com.yjg.ec.platform.login.dao.CustomerUserDao;
import com.yjg.ec.platform.login.handle.OpenIdLoginRealm;

/**
 * Created by zhangyunfei on 22/01/2017.
 */
@Component
public class CustomerOpenIdRealm implements OpenIdLoginRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOpenIdRealm.class);
	@Resource
	private CustomerUserDao customerUserDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private WeixinUserinfoApi weixinUserinfoApi;

	public static String buildUserName() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.replace("-", "");
	}

	@Override
	public LoginUser loadUserByOpenId(String oauthServerType, String code) {
		// call wechat api get openid
		WeixinUserinfoDto dto = weixinUserinfoApi.getUserInfo(code);

		LoginUser loginUser = patientUserDao.getPatientLoginByWechatOpenId(dto.getOpenId());
		if (loginUser == null) {
			PatientUser patientUser = new PatientUser();
			patientUser.setUsername(buildUserName());
			patientUser.setWechatOpenId(dto.getOpenId());
			patientUser.setWechatUnionId(dto.getUnionId());
			long row = patientUserDao.addPatient(patientUser);
			if (row > 0) {
				if (patientDao.initPatient(patientUser.getId()) > 0) {
					loginUser = patientUserDao.getPatientLoginByWechatOpenId(dto.getOpenId());
				}
			}
		}

		return loginUser;
	}
}