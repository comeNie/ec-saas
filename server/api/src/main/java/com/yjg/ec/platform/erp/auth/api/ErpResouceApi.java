package com.yjg.ec.platform.erp.auth.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;

import java.util.List;

@RestApi
public interface ErpResouceApi {

	/**
	 * 查询资源
	 */
	@RequestMapping("/queryErpResouce")
	public List<ErpResouceResultDto> queryErpResouce(Integer id);

	/**
	 * 查询权限下的资源
	 * 
	 * @param authority_id
	 *            权限Id
	 * @return
	 */
	@RequestMapping("/queryErpResouceList")
	public List<ErpResouceResultDto> queryErpResouceList(Integer authority_id);

	/**
	 * 增加资源
	 * 
	 * @param erpResouce
	 * @return 0:失败，大于0：成功，-1：没有对应权限
	 */
	@RequestMapping("/saveErpResouce")
	public Integer saveErpResouce(ErpResouceParamDto erpResouceParamDto);

	/**
	 * 修改资源
	 * 
	 * @param erpResouce
	 * @return
	 */
	@RequestMapping("/updateErpResouce")
	public Integer updateErpResouce(ErpResouceParamDto erpResouceParamDto);

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteErpResouce")
	public Integer deleteErpResouce(Integer id);

	/**
	 * 根据用户id获取用户所有权限码集合
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getResouceByUserId")
	public List<ErpResouceResultDto> getResouceByUserId(Integer userId);

	/**
	 * 根据父id获取子菜单集合
	 *
	 * @param pid
	 * @return
	 */
	@RequestMapping("/queryErpResouceListByPid")
	public List<ErpResouceResultDto> queryErpResouceListByPid(Integer pid);

	/**
	 * 根据条件获取菜单集合
	 *
	 * @param erpResouce
	 * @return
	 */
	@RequestMapping("/queryErpResouceListByCondition")
	public List<ErpResouceResultDto> queryErpResouceListByCondition(ErpResouceParamDto erpResouceParamDto);
}
