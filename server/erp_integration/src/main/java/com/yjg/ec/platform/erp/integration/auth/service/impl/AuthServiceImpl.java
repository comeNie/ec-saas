package com.yjg.ec.platform.erp.integration.auth.service.impl;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.AuthLdapApi;
import com.yjg.ec.platform.erp.auth.api.ErpAuthorityApi;
import com.yjg.ec.platform.erp.auth.api.ErpResouceApi;
import com.yjg.ec.platform.erp.auth.api.ErpUserApi;
import com.yjg.ec.platform.erp.auth.param.dto.AuthLdapParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthLogParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;
import com.yjg.ec.platform.erp.integration.auth.service.AuthService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gus on 2015/8/28.
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Resource
	private ErpUserApi erpUserApi;

	@Resource
	private ErpAuthorityApi erpAuthorityApi;

	@Resource
	private ErpResouceApi erpResouceApi;

	@Resource
	private AuthLdapApi authLdapApi;

	/**
	 * 根据登录账号获取用户信息
	 *
	 * @param account
	 * @return
	 */
	@Override
	public ErpUserResultDto getUserByLoginName(String account) {
		return erpUserApi.queryUserByLoginName(account);
	}

	/**
	 * 根据用户账号获取相应资源权限集合
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getAuthCodes(Integer userId) {
		return erpAuthorityApi.getAuthCodes(userId);
	}

	/**
	 * 插入一条用户表记录
	 *
	 * @param erpUser
	 */
	@Override
	public Integer saveUser(ErpUserParamDto erpUserParamDto) {
		return erpUserApi.saveErpUser(erpUserParamDto);
	}

	/**
	 * 保存一条日志
	 *
	 * @param authLog
	 */
	@Override
	public void saveAuthLog(ErpAuthLogParamDto erpAuthLogParamDto) {

	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<ErpResouceResultDto> getResouceByUserId(Integer userId) {
		return erpResouceApi.getResouceByUserId(userId);
	}

	/**
	 * 通过AD验证
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	@Override
	public boolean authenticate(String userName, String password) {
		AuthLdapParamDto authLdapDto = new AuthLdapParamDto();
		authLdapDto.setUserCn(userName);
		authLdapDto.setCredentials(password);
		try {
			return authLdapApi.authenticate(authLdapDto);
		} catch (Exception e) {
			throw new IncorrectCredentialsException("通过AD验证异常", e.getCause());
		}
	}

}
