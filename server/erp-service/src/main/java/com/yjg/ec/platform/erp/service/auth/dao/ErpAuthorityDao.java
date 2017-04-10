package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityEntity;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityRelationEntity;

import java.util.List;

@MyBatisRepository
public interface ErpAuthorityDao {

	/**
	 * 根据权限主键id获取权限信息
	 *
	 * @param id
	 * @return
	 */
	List<ErpAuthorityEntity> queryErpAuthority(@Param(value = "id") Integer id);

	/**
	 * 插入一条权限信息
	 *
	 * @param erpAuthority
	 * @return
	 */
	Integer saveErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 更新一条权限信息
	 *
	 * @param erpAuthority
	 * @return
	 */
	Integer updateErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 删除一条权限信息
	 *
	 * @param id
	 * @return
	 */
	Integer deleteAuthority(@Param(value = "id") Integer id);

	/**
	 * 根据条件查询权限集合
	 *
	 * @param erpAuthority
	 * @return
	 */
	List<ErpAuthorityEntity> queryErpAuthorityList(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	List<String> getAuthCodes(@Param(value = "id") Integer userId);

	/**
	 * 获取有上下级关系的权限关系
	 *
	 * @return
	 */
	List<ErpAuthorityRelationEntity> queryErpAuthrityRelationList();
}
