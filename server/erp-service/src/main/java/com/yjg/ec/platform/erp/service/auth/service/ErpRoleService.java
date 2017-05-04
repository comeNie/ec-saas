package com.yjg.ec.platform.erp.service.auth.service;

import org.dozer.Mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserRoleResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpRoleDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserRoleDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpRoleEntity;
import com.yjg.ec.platform.erp.service.auth.entity.ErpUserRoleEntity;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErpRoleService {

	// private static Logger logger =
	// LoggerFactory.getLogger(ErpRoleService.class);

	@Resource
	private ErpRoleDao erRoleDao;

	@Resource
	private ErpUserRoleDao erpUserRoleDao;

	@Resource
	private ErpUserDao erpUserDao;

	@Resource
	private Mapper mapper;

	/**
	 * 查询角色
	 */
	public List<ErpRoleResultDto> queryErpRole(Integer id) {
		List<ErpRoleEntity> entityList = erRoleDao.queryErpRole(id);
		List<ErpRoleResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpRoleResultDto dto = mapper.map(entity, ErpRoleResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 查询角色列表
	 */
	public List<ErpRoleResultDto> queryErpRoleList(ErpRoleParamDto erpRoleParamDto) {
		List<ErpRoleEntity> entityList = erRoleDao.queryErpRoleList(erpRoleParamDto);
		List<ErpRoleResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpRoleResultDto dto = mapper.map(entity, ErpRoleResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 增加角色
	 *
	 * @param erpRole
	 * @return
	 */
	@Transactional
	public Integer saveErpRole(ErpRoleParamDto erpRoleParamDto) {
		int rows = erRoleDao.saveErpRole(erpRoleParamDto);
		return rows;
	}

	/**
	 * 修改角色
	 *
	 * @param erpRole
	 * @return
	 */
	@Transactional
	public Integer updateErpRole(ErpRoleParamDto erpRoleParamDto) {
		int rows = erRoleDao.updateErpRole(erpRoleParamDto);
		return rows;
	}

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	public Integer deleteErpRole(Integer id) {
		int rows = erRoleDao.deleteErpRole(id);
		return rows;
	}

	/**
	 * 查询角色下的用户列表
	 *
	 * @param user_id
	 * @return
	 */
	public List<ErpUserRoleResultDto> queryErpUserRoleList(Integer user_id) {
		List<ErpUserRoleEntity> entityList = erpUserRoleDao.queryErpUserRoleList(user_id);
		List<ErpUserRoleResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpUserRoleResultDto dto = mapper.map(entity, ErpUserRoleResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 保存用户角色关联
	 *
	 * @param erpUserRole
	 * @return
	 */
	public Integer saveErpUserRole(ErpUserRoleParamDto erpUserRoleParamDto) {
		return erpUserRoleDao.saveErpUserRole(erpUserRoleParamDto);
	}

	/**
	 * 解除用户角色关联
	 *
	 * @param userId
	 * @return
	 */
	public Integer deleteErpUserRole(Integer userId) {
		return erpUserRoleDao.deleteErpUserRole(userId);
	}

}
