package com.yjg.ec.platform.erp.integration.auth.service;

import java.util.List;

import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthLogParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;

public interface AuthService {

	/**
	 * 根据登录账号获取用户信息
	 * 
	 * @param account
	 * @return
	 */
	ErpUserResultDto getUserByLoginName(String account);

	/**
	 * 根据用户账号获取相应资源权限集合
	 * 
	 * @param userId
	 * @return
	 */
	List<String> getAuthCodes(Integer userId);

	/**
	 * 插入一条用户表记录
	 * 
	 * @param erpUser
	 */
	Integer saveUser(ErpUserParamDto erpUserParamDto);

	/**
	 * 保存一条日志
	 * 
	 * @param authLog
	 */
	void saveAuthLog(ErpAuthLogParamDto erpAuthLogParamDto);

	/**
	 * 根据用户id获取用户所有权限码集合
	 * 
	 * @param userId
	 * @return
	 */
	List<ErpResouceResultDto> getResouceByUserId(Integer userId);

	/**
	 * 通过AD验证
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	boolean authenticate(String userName, String password);
}
