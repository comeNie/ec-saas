package com.yjg.ec.platform.erp.service.auth.service;

import java.util.List;

import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;

public interface ErpAuthorityService {

	/**
	 * 根据权限主键id获取权限信息
	 * 
	 * @param id
	 * @return
	 */
	public List<ErpAuthorityResultDto> queryErpAuthority(Integer id);

	/**
	 * 增加权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	public Integer saveErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 修改权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	public Integer updateErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 删除权限
	 *
	 * @return
	 */
	public Integer deleteAuthority(Integer id);

	/**
	 * 查询权限列表
	 *
	 * @param erpAuthority
	 * @return
	 */
	public List<ErpAuthorityResultDto> queryErpAuthorityList(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	public List<String> getAuthCodes(Integer userId);

	/**
	 * 获取有上下级关系的权限关系
	 *
	 * @return
	 */
	public List<ErpAuthorityRelationResultDto> queryErpAuthrityRelationList();

}
