package com.yjg.ec.platform.erp.service.auth.api;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpResouceApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.service.auth.service.ErpResouceService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ErpResouceApiImpl implements ErpResouceApi {

	@Resource
	private ErpResouceService erpResouceService;

	@Override
	public List<ErpResouceResultDto> queryErpResouce(Integer id) {
		return erpResouceService.queryErpResouce(id);
	}

	@Override
	public List<ErpResouceResultDto> queryErpResouceList(Integer authority_id) {
		return erpResouceService.queryErpResouceList(authority_id);
	}

	@Override
	public Integer saveErpResouce(ErpResouceParamDto erpResouceParamDto) {
		return erpResouceService.saveErpResouce(erpResouceParamDto);
	}

	@Override
	public Integer updateErpResouce(ErpResouceParamDto erpResouceParamDto) {
		return erpResouceService.updateErpResouce(erpResouceParamDto);
	}

	@Override
	public Integer deleteErpResouce(Integer id) {
		return erpResouceService.deleteErpResouce(id);
	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<ErpResouceResultDto> getResouceByUserId(Integer userId) {
		return erpResouceService.getResouceByUserId(userId);
	}

	/**
	 * 根据父id获取子菜单集合
	 *
	 * @param pid
	 * @return
	 */
	@Override
	public List<ErpResouceResultDto> queryErpResouceListByPid(Integer pid) {
		return erpResouceService.queryErpResouceListByPid(pid);
	}

	/**
	 * 根据条件获取菜单集合
	 *
	 * @param erpResouce
	 * @return
	 */
	@Override
	public List<ErpResouceResultDto> queryErpResouceListByCondition(ErpResouceParamDto erpResouceParamDto) {
		return erpResouceService.queryErpResouceListByCondition(erpResouceParamDto);
	}
}
