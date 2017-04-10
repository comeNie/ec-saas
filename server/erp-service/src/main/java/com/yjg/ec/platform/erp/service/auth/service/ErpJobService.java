package com.yjg.ec.platform.erp.service.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnJobParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpJobResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpJobDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserOnJobDao;

@Service
public class ErpJobService {

	private static Logger logger = LoggerFactory.getLogger(ErpJobService.class);

	@Resource
	private ErpJobDao erpJobDao;

	@Resource
	private ErpUserOnJobDao erpUserOnJobDao;

	public ErpJobResultDto queryJob(Integer id) {
		return erpJobDao.queryJob(id);
	}

	public Integer saveJob(ErpJobParamDto erpJobParamDto) {
		return erpJobDao.saveJob(erpJobParamDto);
	}

	public List<ErpJobResultDto> queryJobList() {
		return erpJobDao.queryJobList();
	}

	public Integer updateJob(ErpJobParamDto erpJobParamDto) {
		return erpJobDao.updateJob(erpJobParamDto);
	}

	public Integer deleteJob(Integer id) {
		return erpJobDao.deleteJob(id);
	}

	@Transactional
	public Integer softDeleteJob(ErpJobParamDto erpJobParamDto) {
		this.deleteUserOnJob(erpJobParamDto.getId());
		return erpJobDao.softDeleteJob(erpJobParamDto);
	}

	@Transactional
	public Integer saveUserOnJob(ErpUserOnJobParamDto erpUserOnJobParamDto) {
		this.deleteUserOnJob(erpUserOnJobParamDto);
		return erpUserOnJobDao.saveUserOnJob(erpUserOnJobParamDto);
	}

	public Integer deleteUserOnJob(Integer jobId) {
		ErpUserOnJobParamDto uob = new ErpUserOnJobParamDto();
		uob.setJob_id(jobId);
		return this.deleteUserOnJob(uob);
	}

	public Integer deleteUserOnJob(ErpUserOnJobParamDto erpUserOnJobParamDto) {
		return erpUserOnJobDao.deleteUserOnJob(erpUserOnJobParamDto);
	}

	public boolean isJobExist(ErpJobParamDto erpJobParamDto) {
		Integer count = erpJobDao.countJobByCode(erpJobParamDto);
		return count == null ? false : count > 0;
	}

	public Integer countUserNumByjob(Integer jobId) {
		return erpUserOnJobDao.countUserNumByJob(jobId);
	}
}
