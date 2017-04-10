package com.yjg.ec.platform.erp.web.auth.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.erp.auth.api.ErpDeptApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.UserContext;
import com.yjg.ec.platform.erp.web.auth.vo.ErpDeptVo;

@Component("erpDeptHandler")
public class ErpDeptHandler extends BaseHandler {

	@Resource
	public ErpDeptApi erpDeptApi;

	public List<ErpDeptVo> queryDeptList() {
		List<ErpDeptVo> result_list = null;
		List<ErpDeptResultDto> dept_list = erpDeptApi.queryDeptList();
		if (null != dept_list) {
			result_list = mapList(dept_list, ErpDeptVo.class);
		}
		return result_list;
	}

	public ErpDeptVo getDept(String deptId) {
		ErpDeptResultDto dto = erpDeptApi.queryDept(Integer.valueOf(deptId));
		return dozerMapper.map(dto, ErpDeptVo.class);
	}

	// 逻辑删除
	public int deleteDept(String deptId) {
		ErpDeptParamDto dto = new ErpDeptParamDto();
		dto.setId((Integer.valueOf(deptId)));
		dto.setModify_id(UserContext.getCurrentUser().getId());
		return erpDeptApi.softDeleteDept(dto);
	}

	public int saveOrUpdate(ErpDeptVo vo) {
		ErpDeptParamDto dto = dozerMapper.map(vo, ErpDeptParamDto.class);
		dto.setCreate_id(UserContext.getCurrentUser().getId());
		dto.setModify_id(UserContext.getCurrentUser().getId());
		if (dto.getId() == null) {
			return erpDeptApi.saveDept(dto);
		} else {
			return erpDeptApi.updateDept(dto);
		}
	}

	public boolean checkIsRepeat(String id, String code) {
		ErpDeptParamDto dto = new ErpDeptParamDto();
		if (id != null)
			dto.setId(Integer.valueOf(id));
		dto.setCode(code);
		return erpDeptApi.isDeptExist(dto);
	}

	public Integer countUsersByDept(String deptId) {
		if (StringUtils.isBlank(deptId))
			return 0;
		return erpDeptApi.countUserNumBydept(Integer.valueOf(deptId));
	}
}
