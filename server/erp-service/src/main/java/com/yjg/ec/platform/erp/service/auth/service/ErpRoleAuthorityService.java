package com.yjg.ec.platform.erp.service.auth.service;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleAuthorityResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpRoleAuthorityDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpRoleAuthorityEntity;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErpRoleAuthorityService {

	private static Logger logger = LoggerFactory.getLogger(ErpRoleAuthorityService.class);

	@Resource
	private ErpRoleAuthorityDao erpRoleAuthorityDao;

	@Resource
	private Mapper mapper;

	/**
	 * 根据角色id获取所有角色权限关联关系
	 * 
	 * @param role_id
	 * @return
	 */
	public List<ErpRoleAuthorityResultDto> queryErpRoleAuthorityList(Integer role_id) {
		logger.info("Begin.....");
		List<ErpRoleAuthorityEntity> entityList = erpRoleAuthorityDao.queryErpRoleAuthorityList(role_id);
		List<ErpRoleAuthorityResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpRoleAuthorityResultDto dto = mapper.map(entity, ErpRoleAuthorityResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 增加角色权限关系
	 *
	 * @param erpRoleAuthority
	 * @return 大于0：成功，-1：角色ID错误，-2：权限ID错误
	 */
	@Transactional
	public Integer saveErpAuthority(ErpRoleAuthorityParamDto erpRoleAuthorityParamDto) {
		int rows = erpRoleAuthorityDao.saveErpRoleAuthority(erpRoleAuthorityParamDto);
		return rows;
	}

	/**
	 * 解除角色权限关系
	 *
	 * @param roleId
	 * @return
	 */
	public Integer deleteErpRoleAuthority(Integer roleId) {
		int rows = erpRoleAuthorityDao.deleteErpRoleAuthority(roleId);
		return rows;
	}

}
