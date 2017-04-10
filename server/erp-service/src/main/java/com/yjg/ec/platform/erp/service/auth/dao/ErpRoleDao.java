package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;

import java.util.List;

@MyBatisRepository
public interface ErpRoleDao {

	/**
	 * 根据角色主键获取角色
	 */
	List<ErpRoleResultDto> queryErpRole(@Param(value = "id") Integer id);

	/**
	 * 获取所有角色列表
	 * 
	 * @return
	 */
	List<ErpRoleResultDto> queryErpRoleList(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 插入一条角色
	 * 
	 * @param erpRole
	 * @return
	 */
	Integer saveErpRole(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 修改角色信息
	 * 
	 * @param erpRole
	 * @return
	 */
	Integer updateErpRole(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 删除一条角色
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteErpRole(@Param(value = "id") Integer id);

}
