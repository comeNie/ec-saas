package com.yjg.ec.platform.erp.service.auth.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpJobApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpJobResultDto;
import com.yjg.ec.platform.erp.service.auth.service.ErpJobService;

@Service
public class ErpJobApiImpl implements ErpJobApi {

	@Resource
	private ErpJobService erpJobService;

	@Override
	public List<ErpJobResultDto> queryJobList() {
		return erpJobService.queryJobList();
	}

	@Override
	public ErpJobResultDto queryJob(Integer id) {
		return erpJobService.queryJob(id);
	}

	@Override
	public Integer saveJob(ErpJobParamDto erpJobParamDto) {
		return erpJobService.saveJob(erpJobParamDto);
	}

	@Override
	public Integer updateJob(ErpJobParamDto erpJobParamDto) {
		return erpJobService.updateJob(erpJobParamDto);
	}

	@Override
	public Integer deleteJob(Integer id) {
		return erpJobService.deleteJob(id);
	}

	@Override
	public Integer softDeleteJob(ErpJobParamDto erpJobParamDto) {
		return erpJobService.softDeleteJob(erpJobParamDto);
	}

	@Override
	public boolean isJobExist(ErpJobParamDto erpJobParamDto) {
		return erpJobService.isJobExist(erpJobParamDto);
	}

	@Override
	public Integer countUserNumByjob(Integer jobId) {
		return erpJobService.countUserNumByjob(jobId);
	}

}
