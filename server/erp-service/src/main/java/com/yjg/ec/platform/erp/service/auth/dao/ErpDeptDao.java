package com.yjg.ec.platform.erp.service.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpDeptEntity;

/**
 * 
 * @author liubin
 *
 */
@MyBatisRepository
public interface ErpDeptDao {

	ErpDeptEntity queryDept(@Param(value = "id") Integer id);

	Integer saveDept(ErpDeptParamDto erpDeptParamDto);

	List<ErpDeptEntity> queryDeptList();

	Integer updateDept(ErpDeptParamDto erpDeptParamDto);

	Integer deleteDept(@Param(value = "id") Integer id);

	Integer softDeleteDept(ErpDeptParamDto erpDeptParamDto);

	Integer countDeptByCode(ErpDeptParamDto erpDeptParamDto);

}
