package com.yjg.ec.platform.erp.auth.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;

/**
 * Created by gus on 2015/8/28.
 */
@RestApi
public interface ErpAuthorityApi {

	/**
	 * 查询权限
	 */
	@RequestMapping("/queryErpAuthority")
	public List<ErpAuthorityResultDto> queryErpAuthority(Integer id);

	/**
	 * 查询权限列表
	 */
	@RequestMapping("/queryErpAuthorityList")
	public List<ErpAuthorityResultDto> queryErpAuthorityList(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 增加权限
	 * 
	 * @param erpAuthority
	 * @return
	 */
	@RequestMapping("/saveErpAuthority")
	public Integer saveErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 修改权限
	 * 
	 * @param erpAuthority
	 * @return
	 */
	@RequestMapping("/updateErpAuthority")
	public Integer updateErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto);

	/**
	 * 删除权限
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAuthority")
	public Integer deleteAuthority(Integer id);

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getAuthCodes")
	public List<String> getAuthCodes(Integer userId);

	/**
	 * 获取有上下级关系的权限关系
	 *
	 * @return
	 */
	@RequestMapping("/queryErpAuthrityRelationList")
	public List<ErpAuthorityRelationResultDto> queryErpAuthrityRelationList();
}
