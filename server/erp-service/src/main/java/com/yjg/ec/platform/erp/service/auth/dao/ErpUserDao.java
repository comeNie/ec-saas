package com.yjg.ec.platform.erp.service.auth.dao;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpUserEntity;

import java.util.List;

@MyBatisRepository
public interface ErpUserDao {

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param id
	 * @return
	 */
	List<ErpUserEntity> queryErpUser(@Param(value = "id") Integer id);

	/**
	 * 保存一条用户信息
	 *
	 * @param user
	 * @return
	 */
	Integer saveErpUser(ErpUserParamDto erpUserParamDto);

	/**
	 * 更新一条用户信息
	 * 
	 * @param erpUser
	 * @return
	 */
	Integer updateErpUser(ErpUserParamDto erpUserParamDto);

	/**
	 * 根据登录名获取用户信息
	 *
	 * @param userName
	 * @return
	 */
	ErpUserEntity queryUserByLoginName(@Param(value = "login_name") String userName);

	/**
	 * 查询所有可用用户集合
	 *
	 * @return
	 */
	List<ErpUserEntity> queryErpUserList(ErpUserParamDto erpUserParamDto);

	/**
	 * 根据部门编号和职位编号查询用户登录名
	 * 
	 * @param deptCode
	 * @param jobCode
	 * @return
	 */
	List<String> queryUserNames(@Param("deptCode") String deptCode, @Param("jobCode") String jobCode);
}
