package com.yjg.ec.platform.erp.service.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpAuthorityDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpResouceDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityEntity;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ErpResouceService {

	@Resource
	private ErpResouceDao erpResouceDao;
	@Resource
	private ErpAuthorityDao erpAuthorityDao;

	/**
	 * 根据主键id获取资源（菜单）信息
	 *
	 * @param id
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouce(Integer id) {
		List<ErpResouceResultDto> resList = erpResouceDao.queryErpResouce(id);
		return resList;
	}

	/**
	 * 增加资源
	 *
	 * @param erpResouce
	 * @return 0:失败，大于0：成功，-1：没有对应权限
	 */
	@Transactional
	public Integer saveErpResouce(ErpResouceParamDto erpResouceParamDto) {
		System.out.println("-------" + erpResouceParamDto.getAuthority_id());
		List<ErpAuthorityEntity> list = erpAuthorityDao.queryErpAuthority(erpResouceParamDto.getAuthority_id());
		if (list.size() == 0) {
			return -1;
		}
		int rows = erpResouceDao.saveErpResouce(erpResouceParamDto);
		return rows;
	}

	/**
	 * 修改资源
	 *
	 * @param erpResouce
	 * @return
	 */
	@Transactional
	public Integer updateErpResouce(ErpResouceParamDto erpResouceParamDto) {
		int rows = erpResouceDao.updateErpResouce(erpResouceParamDto);
		return rows;
	}

	/**
	 * 删除资源
	 *
	 * @param id
	 * @return
	 */
	public Integer deleteErpResouce(Integer id) {
		int rows = erpResouceDao.deleteErpResouce(id);
		return rows;
	}

	/**
	 * 查询权限下的资源
	 *
	 * @param authority_id
	 *            权限Id
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouceList(Integer authority_id) {
		List<ErpResouceResultDto> resList = erpResouceDao.queryErpResouceList(authority_id);
		return resList;
	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	public List<ErpResouceResultDto> getResouceByUserId(Integer userId) {
		return erpResouceDao.getResouceByUserId(userId);
	}

	/**
	 * 根据父id获取子菜单集合
	 *
	 * @param pid
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouceListByPid(Integer pid) {
		return erpResouceDao.queryErpResouceListByPid(pid);
	}

	/**
	 * 根据条件获取菜单集合
	 *
	 * @param erpResouce
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouceListByCondition(ErpResouceParamDto erpResouceParamDto) {
		return erpResouceDao.queryErpResouceListByCondition(erpResouceParamDto);
	}

}
