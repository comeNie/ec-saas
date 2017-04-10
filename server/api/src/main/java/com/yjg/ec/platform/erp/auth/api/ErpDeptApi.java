package com.yjg.ec.platform.erp.auth.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpDeptParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpDeptResultDto;

/**
 * 
 * @author liubin
 *
 */
@RestApi
public interface ErpDeptApi {

	@RequestMapping("/queryDeptList")
	public List<ErpDeptResultDto> queryDeptList();

	@RequestMapping("/queryDept")
	public ErpDeptResultDto queryDept(Integer id);

	@RequestMapping("/saveDept")
	public Integer saveDept(ErpDeptParamDto dept);

	@RequestMapping("/updateDept")
	public Integer updateDept(ErpDeptParamDto dept);

	@RequestMapping("/deleteDept")
	public Integer deleteDept(Integer id);

	@RequestMapping("/isDeptExist")
	public boolean isDeptExist(ErpDeptParamDto dto);

	@RequestMapping("/softDeleteDept")
	public Integer softDeleteDept(ErpDeptParamDto dept);

	@RequestMapping("/countUserNumBydept")
	public Integer countUserNumBydept(Integer deptId);

}
