package com.yjg.ec.platform.erp.auth.api;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleAuthorityResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserRoleResultDto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

@RestApi
public interface ErpRoleApi {

	/**
	 * 查询资源
	 */
	@RequestMapping("/queryErpRole")
	public List<ErpRoleResultDto> queryErpRole(Integer id);

	/**
	 * 查询角色列表
	 */
	@RequestMapping("/queryErpRoleList")
	public List<ErpRoleResultDto> queryErpRoleList(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 增加角色
	 */
	@RequestMapping("/saveErpRole")
	public Integer saveErpRole(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 修改角色
	 *
	 * @param erpRole
	 * @return
	 */
	@RequestMapping("/updateErpRole")
	public Integer updateErpRole(ErpRoleParamDto erpRoleParamDto);

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteErpRole")
	public Integer deleteErpRole(Integer id);

	/**
	 * 查询角色下的权限
	 */
	@RequestMapping("/queryErpRoleAuthorityList")
	public List<ErpRoleAuthorityResultDto> queryErpRoleAuthorityList(Integer role_id);

	/**
	 * 为角色增加权限
	 */
	@RequestMapping("/saveErpRoleAuthority")
	public Integer saveErpRoleAuthority(ErpRoleAuthorityParamDto erpRoleAuthorityParamDto);

	/**
	 * 查询角色下的用户列表
	 *
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/queryErpUserRoleList")
	public List<ErpUserRoleResultDto> queryErpUserRoleList(Integer user_id);

	/**
	 * 保存用户角色关联
	 *
	 * @param erpUserRole
	 * @return
	 */
	@RequestMapping("/saveErpUserRole")
	public Integer saveErpUserRole(ErpUserRoleParamDto erpUserRoleParamDto);

	/**
	 * 解除用户角色关联
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteErpUserRole")
	public Integer deleteErpUserRole(Integer userId);

	/**
	 * 解除角色权限关系
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/deleteErpRoleAuthority")
	public Integer deleteErpRoleAuthority(Integer roleId);
}
