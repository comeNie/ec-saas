package com.yjg.ec.platform.erp.service.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnJobParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpJobResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpJobDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserOnJobDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpJobEntity;

@Service
public class ErpJobService {

	@Resource
	private ErpJobDao erpJobDao;

	@Resource
	private ErpUserOnJobDao erpUserOnJobDao;

	@Resource
	private Mapper mapper;

	public ErpJobResultDto queryJob(Integer id) {
		ErpJobEntity entity = erpJobDao.queryJob(id);
		ErpJobResultDto dto = mapper.map(entity, ErpJobResultDto.class);
		return dto;
	}

	public Integer saveJob(ErpJobParamDto erpJobParamDto) {
		return erpJobDao.saveJob(erpJobParamDto);
	}

	public List<ErpJobResultDto> queryJobList() {
		List<ErpJobEntity> entityList = erpJobDao.queryJobList();
		List<ErpJobResultDto> dtoList = new ArrayList<>();
		dtoList = entityList.stream().map(entity -> mapper.map(entity, ErpJobResultDto.class))
				.collect(Collectors.toList());
		return dtoList;
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
