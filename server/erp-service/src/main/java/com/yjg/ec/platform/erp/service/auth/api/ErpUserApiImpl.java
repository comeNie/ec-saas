package com.yjg.ec.platform.erp.service.auth.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.ErpUserApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;
import com.yjg.ec.platform.erp.service.auth.service.ErpUserService;

/**
 * Created by gus on 2015/8/28.
 */
@Service
public class ErpUserApiImpl implements ErpUserApi {

	@Resource
	private ErpUserService erpUserService;

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param id
	 */
	@Override
	public List<ErpUserResultDto> queryErpUser(Integer id) {
		return erpUserService.queryErpUser(id);
	}

	@Override
	public ErpUserResultDto queryUserByLoginName(String userName) {
		return erpUserService.queryUserByLoginName(userName);
	}

	/**
	 * 插入一条用户记录
	 *
	 * @param user
	 * @return
	 */
	@Override
	public Integer saveErpUser(ErpUserParamDto erpUserParamDto) {
		return erpUserService.saveErpUser(erpUserParamDto);
	}

	/**
	 * 更新一条用户信息
	 *
	 * @param erpUser
	 * @return
	 */
	@Override
	public Integer updateErpUser(ErpUserParamDto erpUserParamDto) {
		return erpUserService.updateErpUser(erpUserParamDto);
	}

	/**
	 * 查询所有可用用户集合
	 *
	 * @param user
	 * @return
	 */
	@Override
	public List<ErpUserResultDto> queryErpUserList(ErpUserParamDto erpUserParamDto) {
		return erpUserService.queryErpUserList(erpUserParamDto);
	}

	@Override
	public List<String> queryUserNames(ErpUserParamDto erpUserParamDto) {
		return erpUserService.queryUserNames(erpUserParamDto.getDept_code(), erpUserParamDto.getJob_code());
	}
}
