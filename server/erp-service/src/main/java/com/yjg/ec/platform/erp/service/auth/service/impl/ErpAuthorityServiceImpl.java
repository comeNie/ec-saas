package com.yjg.ec.platform.erp.service.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpAuthorityDao;
import com.yjg.ec.platform.erp.service.auth.service.ErpAuthorityService;

import javax.annotation.Resource;

import java.util.List;

@Service
public class ErpAuthorityServiceImpl implements ErpAuthorityService {

	private static Logger logger = LoggerFactory.getLogger(ErpAuthorityServiceImpl.class);

	@Resource
	private ErpAuthorityDao erpAuthorityDao;

	/**
	 * 根据权限主键id获取权限信息
	 * 
	 * @param id
	 * @return
	 */
	public List<ErpAuthorityResultDto> queryErpAuthority(Integer id) {
		List<ErpAuthorityResultDto> resList = erpAuthorityDao.queryErpAuthority(id);
		return resList;
	}

	/**
	 * 增加权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	@Transactional
	public Integer saveErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto) {
		int rows = erpAuthorityDao.saveErpAuthority(erpAuthorityParamDto);
		return rows;
	}

	/**
	 * 修改权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	@Transactional
	public Integer updateErpAuthority(ErpAuthorityParamDto erpAuthorityParamDto) {
		int rows = erpAuthorityDao.updateErpAuthority(erpAuthorityParamDto);
		return rows;
	}

	/**
	 * 删除权限
	 *
	 * @return
	 */
	public Integer deleteAuthority(Integer id) {
		int rows = erpAuthorityDao.deleteAuthority(id);
		return rows;
	}

	/**
	 * 查询权限列表
	 *
	 * @param erpAuthority
	 * @return
	 */
	public List<ErpAuthorityResultDto> queryErpAuthorityList(ErpAuthorityParamDto erpAuthorityParamDto) {
		List<ErpAuthorityResultDto> resList = erpAuthorityDao.queryErpAuthorityList(erpAuthorityParamDto);
		return resList;
	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	public List<String> getAuthCodes(Integer userId) {
		return erpAuthorityDao.getAuthCodes(userId);
	}

	/**
	 * 获取有上下级关系的权限关系
	 *
	 * @return
	 */
	public List<ErpAuthorityRelationResultDto> queryErpAuthrityRelationList() {
		return erpAuthorityDao.queryErpAuthrityRelationList();
	}

}
