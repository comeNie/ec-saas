package com.yjg.ec.platform.erp.service.auth.dao;

import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnDeptParamDto;

/**
 * 
 * @author liubin
 *
 */
@MyBatisRepository
public interface ErpUserOnDeptDao {

	Integer deleteUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto);

	Integer saveUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto);

	Integer updateUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto);

	Integer countUserNumBydept(Integer deptId);

}
