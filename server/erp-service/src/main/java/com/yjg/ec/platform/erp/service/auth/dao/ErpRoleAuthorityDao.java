package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleAuthorityParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpRoleAuthorityEntity;

import java.util.List;

@MyBatisRepository
public interface ErpRoleAuthorityDao {

	/**
	 * 根据角色id获取所有角色权限关联关系
	 * 
	 * @param role_id
	 * @return
	 */
	List<ErpRoleAuthorityEntity> queryErpRoleAuthorityList(@Param(value = "role_id") Integer role_id);

	/**
	 * 保存一条角色权限关联关系
	 * 
	 * @param erpRoleAuthority
	 * @return
	 */
	Integer saveErpRoleAuthority(ErpRoleAuthorityParamDto erpRoleAuthorityParamDto);

	/**
	 * 根据角色id删除所有角色权限管理关系
	 * 
	 * @param roleId
	 * @return
	 */
	Integer deleteErpRoleAuthority(@Param(value = "id") Integer roleId);
}
