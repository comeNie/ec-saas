package com.yjg.ec.platform.erp.service.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserRoleResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpRoleDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserRoleDao;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ErpRoleService {

	private static Logger logger = LoggerFactory.getLogger(ErpRoleService.class);

	@Resource
	private ErpRoleDao erRoleDao;

	@Resource
	private ErpUserRoleDao erpUserRoleDao;

	@Resource
	private ErpUserDao erpUserDao;

	/**
	 * 查询角色
	 */
	@SuppressWarnings("unchecked")
	public List<ErpRoleResultDto> queryErpRole(Integer id) {
		List<ErpRoleResultDto> resList = erRoleDao.queryErpRole(id);
		return resList;
	}

	/**
	 * 查询角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<ErpRoleResultDto> queryErpRoleList(ErpRoleParamDto erpRoleParamDto) {
		List<ErpRoleResultDto> resList = erRoleDao.queryErpRoleList(erpRoleParamDto);
		return resList;
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
		return erpUserRoleDao.queryErpUserRoleList(user_id);
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
