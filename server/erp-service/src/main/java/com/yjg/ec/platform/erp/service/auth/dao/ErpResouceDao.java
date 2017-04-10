package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;

import java.util.List;

@MyBatisRepository
public interface ErpResouceDao {

	/**
	 * 根据主键id获取资源（菜单）信息
	 *
	 * @param id
	 * @return
	 */
	List<ErpResouceResultDto> queryErpResouce(@Param(value = "id") Integer id);

	/**
	 * 插入一条资源（菜单）信息
	 *
	 * @param erpResouce
	 * @return
	 */
	Integer saveErpResouce(ErpResouceParamDto erpResouceParamDto);

	/**
	 * 更新一条资源（菜单）信息
	 * 
	 * @param erpResouce
	 * @return
	 */
	Integer updateErpResouce(ErpResouceParamDto erpResouceParamDto);

	/**
	 * 删除一条资源（菜单）信息
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteErpResouce(@Param(value = "id") Integer id);

	/**
	 * 根据权限id获取菜单
	 *
	 * @param id
	 * @return
	 */
	List<ErpResouceResultDto> queryErpResouceList(@Param(value = "authority_id") Integer id);

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	List<ErpResouceResultDto> getResouceByUserId(@Param(value = "id") Integer userId);

	/**
	 * 根据父id获取子菜单集合
	 *
	 * @param pid
	 * @return
	 */
	List<ErpResouceResultDto> queryErpResouceListByPid(@Param(value = "pid") Integer pid);

	/**
	 * 根据条件获取菜单集合
	 *
	 * @param erpResouce
	 * @return
	 */
	List<ErpResouceResultDto> queryErpResouceListByCondition(ErpResouceParamDto erpResouceParamDto);
}
