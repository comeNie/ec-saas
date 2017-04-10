package com.yjg.ec.platform.erp.service.auth.api;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpRoleApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleAuthorityResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserRoleResultDto;
import com.yjg.ec.platform.erp.service.auth.service.ErpRoleAuthorityService;
import com.yjg.ec.platform.erp.service.auth.service.ErpRoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ErpRoleApiImpl implements ErpRoleApi {

	@Resource
	private ErpRoleService erpRoleService;

	@Resource
	private ErpRoleAuthorityService erpRoleAuthorityService;

	@Override
	public List<ErpRoleResultDto> queryErpRole(Integer id) {
		return erpRoleService.queryErpRole(id);
	}

	@Override
	public List<ErpRoleResultDto> queryErpRoleList(ErpRoleParamDto erpRoleParamDto) {
		return erpRoleService.queryErpRoleList(erpRoleParamDto);
	}

	@Override
	public Integer saveErpRole(ErpRoleParamDto erpRoleParamDto) {
		return erpRoleService.saveErpRole(erpRoleParamDto);
	}

	@Override
	public Integer updateErpRole(ErpRoleParamDto erpRoleParamDto) {
		return erpRoleService.updateErpRole(erpRoleParamDto);
	}

	@Override
	public Integer deleteErpRole(Integer id) {
		return erpRoleService.deleteErpRole(id);
	}

	@Override
	public List<ErpRoleAuthorityResultDto> queryErpRoleAuthorityList(Integer role_id) {
		return erpRoleAuthorityService.queryErpRoleAuthorityList(role_id);
	}

	/**
	 * 为角色增加权限
	 */
	@Override
	public Integer saveErpRoleAuthority(ErpRoleAuthorityParamDto erpRoleAuthorityParamDto) {
		return erpRoleAuthorityService.saveErpAuthority(erpRoleAuthorityParamDto);
	}

	/**
	 * 查询角色下的用户列表
	 *
	 * @param user_id
	 * @return
	 */
	@Override
	public List<ErpUserRoleResultDto> queryErpUserRoleList(Integer user_id) {
		return erpRoleService.queryErpUserRoleList(user_id);
	}

	/**
	 * 保存用户角色关联
	 *
	 * @param erpUserRole
	 * @return
	 */
	@Override
	public Integer saveErpUserRole(ErpUserRoleParamDto erpUserRoleParamDto) {
		return erpRoleService.saveErpUserRole(erpUserRoleParamDto);
	}

	/**
	 * 解除用户角色关联
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public Integer deleteErpUserRole(Integer userId) {
		return erpRoleService.deleteErpUserRole(userId);
	}

	/**
	 * 解除角色权限关系
	 *
	 * @param roleId
	 * @return
	 */
	@Override
	public Integer deleteErpRoleAuthority(Integer roleId) {
		return erpRoleAuthorityService.deleteErpRoleAuthority(roleId);
	}
}
