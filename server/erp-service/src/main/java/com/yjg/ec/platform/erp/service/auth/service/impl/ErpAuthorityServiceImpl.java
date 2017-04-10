package com.yjg.ec.platform.erp.service.auth.service.impl;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpAuthorityDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityEntity;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityRelationEntity;
import com.yjg.ec.platform.erp.service.auth.service.ErpAuthorityService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErpAuthorityServiceImpl implements ErpAuthorityService {

	@Resource
	private ErpAuthorityDao erpAuthorityDao;

	@Resource
	private Mapper mapper;

	/**
	 * 根据权限主键id获取权限信息
	 * 
	 * @param id
	 * @return
	 */
	public List<ErpAuthorityResultDto> queryErpAuthority(Integer id) {
		List<ErpAuthorityEntity> entityList = erpAuthorityDao.queryErpAuthority(id);
		List<ErpAuthorityResultDto> resultList = new ArrayList<>();
		resultList = entityList.stream().map(entity -> mapper.map(entity, ErpAuthorityResultDto.class))
				.collect(Collectors.toList());
		return resultList;
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
		List<ErpAuthorityEntity> entityList = erpAuthorityDao.queryErpAuthorityList(erpAuthorityParamDto);
		List<ErpAuthorityResultDto> resultList = new ArrayList<>();
		resultList = entityList.stream().map(entity -> mapper.map(entity, ErpAuthorityResultDto.class))
				.collect(Collectors.toList());
		return resultList;
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
		List<ErpAuthorityRelationEntity> entityList = erpAuthorityDao.queryErpAuthrityRelationList();
		List<ErpAuthorityRelationResultDto> dtoList = entityList.stream()
				.map(entity -> mapper.map(entity, ErpAuthorityRelationResultDto.class)).collect(Collectors.toList());
		return dtoList;
	}

}
