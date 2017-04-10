package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpUserRoleEntity;

import java.util.List;

@MyBatisRepository
public interface ErpUserRoleDao {

	/**
	 * 根据用户id获取用户已绑定角色集合
	 * 
	 * @param user_id
	 * @return
	 */
	List<ErpUserRoleEntity> queryErpUserRoleList(@Param(value = "user_id") Integer user_id);

	/**
	 * 保存一条用户角色关联信息
	 * 
	 * @param erpUserRole
	 * @return
	 */
	Integer saveErpUserRole(ErpUserRoleParamDto erpUserRoleParamDto);

	/**
	 * 删除用户所有关联角色
	 * 
	 * @param userId
	 * @return
	 */
	Integer deleteErpUserRole(@Param(value = "user_id") Integer userId);
}
