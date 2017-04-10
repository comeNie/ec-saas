package com.yjg.ec.platform.erp.web.auth.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.erp.auth.api.ErpJobApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpJobResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.UserContext;
import com.yjg.ec.platform.erp.web.auth.vo.ErpJobVo;

@Component("erpJobHandler")
public class ErpJobHandler extends BaseHandler {

	@Resource
	public ErpJobApi erpJobApi;

	public List<ErpJobVo> queryJobList() {
		List<ErpJobVo> result_list = null;
		List<ErpJobResultDto> Job_list = erpJobApi.queryJobList();
		if (null != Job_list) {
			result_list = mapList(Job_list, ErpJobVo.class);
		}
		return result_list;
	}

	public ErpJobVo getJob(String jobId) {
		ErpJobResultDto dto = erpJobApi.queryJob(Integer.valueOf(jobId));
		return dozerMapper.map(dto, ErpJobVo.class);
	}

	public int deleteJob(String jobId) {
		ErpJobParamDto dto = new ErpJobParamDto();
		dto.setId(Integer.valueOf(jobId));
		dto.setModify_id(UserContext.getCurrentUser().getId());
		return erpJobApi.softDeleteJob(dto);
	}

	public int saveOrUpdate(ErpJobVo vo) {
		ErpJobParamDto dto = dozerMapper.map(vo, ErpJobParamDto.class);
		dto.setCreate_id(UserContext.getCurrentUser().getId());
		dto.setModify_id(UserContext.getCurrentUser().getId());
		if (dto.getId() == null) {
			return erpJobApi.saveJob(dto);
		} else {
			return erpJobApi.updateJob(dto);
		}
	}

	public boolean checkIsRepeat(String id, String code) {
		ErpJobParamDto dto = new ErpJobParamDto();
		if (id != null)
			dto.setId(Integer.valueOf(id));
		dto.setCode(code);
		return erpJobApi.isJobExist(dto);
	}

	public Object countUsersByJob(String jobId) {
		if (StringUtils.isBlank(jobId))
			return 0;
		return erpJobApi.countUserNumByjob(Integer.valueOf(jobId));
	}
}
