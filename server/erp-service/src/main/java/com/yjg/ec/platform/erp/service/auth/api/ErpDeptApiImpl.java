package com.yjg.ec.platform.erp.service.auth.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpDeptApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;
import com.yjg.ec.platform.erp.service.auth.service.impl.ErpDeptServiceImpl;

@Service
public class ErpDeptApiImpl implements ErpDeptApi {

	@Resource
	private ErpDeptServiceImpl erpDeptService;

	@Override
	public List<ErpDeptResultDto> queryDeptList() {
		return erpDeptService.queryDeptList();
	}

	@Override
	public ErpDeptResultDto queryDept(Integer id) {
		return erpDeptService.queryDept(id);
	}

	@Override
	public Integer saveDept(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptService.saveDept(erpDeptParamDto);
	}

	@Override
	public Integer updateDept(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptService.updateDept(erpDeptParamDto);
	}

	@Override
	public Integer deleteDept(Integer id) {
		return erpDeptService.deleteDept(id);
	}

	@Override
	public boolean isDeptExist(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptService.isDeptExist(erpDeptParamDto);
	}

	@Override
	public Integer softDeleteDept(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptService.softDeleteDept(erpDeptParamDto);
	}

	@Override
	public Integer countUserNumBydept(Integer deptId) {
		return erpDeptService.countUserNumBydept(deptId);
	}
}
