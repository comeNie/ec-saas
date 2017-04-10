package com.yjg.ec.platform.erp.service.auth.api;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpAuthorityApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;
import com.yjg.ec.platform.erp.service.auth.service.impl.ErpAuthorityServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gus on 2015/8/28.
 */
@Service
public class ErpAuthorityApiImpl implements ErpAuthorityApi {

	@Resource
	private ErpAuthorityServiceImpl erpAuthorityService;

	@Override
	public List<ErpAuthorityResultDto> queryErpAuthority(Integer id) {
		return erpAuthorityService.queryErpAuthority(id);
	}

	@Override
	public List<ErpAuthorityResultDto> queryErpAuthorityList(ErpAuthorityParamDto erpAuthorityParamDto) {
		return erpAuthorityService.queryErpAuthorityList(erpAuthorityParamDto);
	}

	@Override
	public Integer saveErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto) {
		return erpAuthorityService.saveErpAuthority(erpAuthorityParamDto);
	}

	@Override
	public Integer updateErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto) {
		return erpAuthorityService.updateErpAuthority(erpAuthorityParamDto);
	}

	@Override
	public Integer deleteAuthority(Integer id) {
		return erpAuthorityService.deleteAuthority(id);
	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getAuthCodes(Integer userId) {
		return erpAuthorityService.getAuthCodes(userId);
	}

	/**
	 * 获取有上下级关系的权限关系
	 *
	 * @return
	 */
	@Override
	public List<ErpAuthorityRelationResultDto> queryErpAuthrityRelationList() {
		return erpAuthorityService.queryErpAuthrityRelationList();
	}
}
