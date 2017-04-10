package com.yjg.ec.platform.erp.service.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;

/**
 * 
 * @author liubin
 *
 */
@MyBatisRepository
public interface ErpDeptDao {

	ErpDeptResultDto queryDept(@Param(value = "id") Integer id);

	Integer saveDept(ErpDeptParamDto erpDeptParamDto);

	List<ErpDeptResultDto> queryDeptList();

	Integer updateDept(ErpDeptParamDto erpDeptParamDto);

	Integer deleteDept(@Param(value = "id") Integer id);

	Integer softDeleteDept(ErpDeptParamDto erpDeptParamDto);

	Integer countDeptByCode(ErpDeptParamDto erpDeptParamDto);

}
