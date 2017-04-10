package com.yjg.ec.platform.erp.auth.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpJobResultDto;

/**
 * 
 * @author liubin
 *
 */
@RestApi
public interface ErpJobApi {

	@RequestMapping("/queryJobList")
	public List<ErpJobResultDto> queryJobList();

	@RequestMapping("/queryJob")
	public ErpJobResultDto queryJob(Integer id);

	@RequestMapping("/saveJob")
	public Integer saveJob(ErpJobParamDto job);

	@RequestMapping("/updateJob")
	public Integer updateJob(ErpJobParamDto job);

	@RequestMapping("/deleteJob")
	public Integer deleteJob(Integer id);

	@RequestMapping("/isJobExist")
	public boolean isJobExist(ErpJobParamDto job);

	@RequestMapping("/softDeleteJob")
	public Integer softDeleteJob(ErpJobParamDto job);

	@RequestMapping("/countUserNumByjob")
	public Integer countUserNumByjob(Integer jobId);

}
