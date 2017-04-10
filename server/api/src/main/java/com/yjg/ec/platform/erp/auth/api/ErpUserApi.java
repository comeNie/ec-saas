package com.yjg.ec.platform.erp.auth.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;

import java.util.List;

/**
 * Created by gus on 2015/8/28.
 */
@RestApi
public interface ErpUserApi {

	/**
	 * 根据用户id获取用户信息
	 */
	@RequestMapping("/queryErpUser")
	List<ErpUserResultDto> queryErpUser(Integer id);

	/**
	 * 根据登录名获取用户信息
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("/queryUserByLoginName")
	ErpUserResultDto queryUserByLoginName(String userName);

	/**
	 * 插入一条用户记录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveErpUser")
	Integer saveErpUser(ErpUserParamDto erpUserParamDto);

	/**
	 * 更新一条用户信息
	 * 
	 * @param erpUser
	 * @return
	 */
	@RequestMapping("/updateErpUser")
	Integer updateErpUser(ErpUserParamDto erpUserParamDto);

	/**
	 * 查询所有可用用户集合
	 * 
	 * @return
	 */
	@RequestMapping("/queryErpUserList")
	List<ErpUserResultDto> queryErpUserList(ErpUserParamDto erpUserParamDto);

	@RequestMapping("/queryUserNames")
	public List<String> queryUserNames(ErpUserParamDto erpUserParamDto);
}
