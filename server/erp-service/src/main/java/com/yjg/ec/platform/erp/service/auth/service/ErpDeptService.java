package com.yjg.ec.platform.erp.service.auth.service;

import java.util.List;

import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;

public interface ErpDeptService {

	public ErpDeptResultDto queryDept(Integer id);

	public Integer saveDept(ErpDeptParamDto erpDeptParamDto);

	public List<ErpDeptResultDto> queryDeptList();

	public Integer updateDept(ErpDeptParamDto erpDeptParamDto);

	public Integer deleteDept(Integer id);

	public Integer softDeleteDept(ErpDeptParamDto erpDeptParamDto);

	public Integer saveUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto);

	public Integer deleteUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto);

	public Integer deleteUserOnDept(Integer deptId);

	public boolean isDeptExist(ErpDeptParamDto erpDeptParamDto);

	public int countUserNumBydept(Integer deptId);
}
