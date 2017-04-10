package com.yjg.ec.platform.erp.service.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpDeptDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserOnDeptDao;
import com.yjg.ec.platform.erp.service.auth.service.ErpDeptService;

@Service
public class ErpDeptServiceImpl implements ErpDeptService {

	private static Logger logger = LoggerFactory.getLogger(ErpDeptServiceImpl.class);

	@Resource
	private ErpDeptDao erpDeptDao;

	@Resource
	private ErpUserOnDeptDao erpUserOnDeptDao;

	public ErpDeptResultDto queryDept(Integer id) {
		return erpDeptDao.queryDept(id);
	}

	public Integer saveDept(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptDao.saveDept(erpDeptParamDto);
	}

	public List<ErpDeptResultDto> queryDeptList() {
		return erpDeptDao.queryDeptList();
	}

	public Integer updateDept(ErpDeptParamDto erpDeptParamDto) {
		return erpDeptDao.updateDept(erpDeptParamDto);
	}

	public Integer deleteDept(Integer id) {
		return erpDeptDao.deleteDept(id);
	}

	@Transactional
	public Integer softDeleteDept(ErpDeptParamDto erpDeptParamDto) {
		this.deleteUserOnDept(erpDeptParamDto.getId());
		return erpDeptDao.softDeleteDept(erpDeptParamDto);
	}

	@Transactional
	public Integer saveUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto) {
		this.deleteUserOnDept(erpUserOnDeptParamDto);
		return erpUserOnDeptDao.saveUserOnDept(erpUserOnDeptParamDto);
	}

	public Integer deleteUserOnDept(ErpUserOnDeptParamDto erpUserOnDeptParamDto) {
		return erpUserOnDeptDao.deleteUserOnDept(erpUserOnDeptParamDto);
	}

	public Integer deleteUserOnDept(Integer deptId) {
		ErpUserOnDeptParamDto erpUserOnDeptParamDto = new ErpUserOnDeptParamDto();
		erpUserOnDeptParamDto.setDept_id(deptId);
		return this.deleteUserOnDept(erpUserOnDeptParamDto);
	}

	public boolean isDeptExist(ErpDeptParamDto erpDeptParamDto) {
		Integer count = erpDeptDao.countDeptByCode(erpDeptParamDto);
		return count == null ? false : count > 0;
	}

	public int countUserNumBydept(Integer deptId) {
		return erpUserOnDeptDao.countUserNumBydept(deptId);
	}
}
