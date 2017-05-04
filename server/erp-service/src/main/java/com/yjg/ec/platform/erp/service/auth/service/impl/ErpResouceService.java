package com.yjg.ec.platform.erp.service.auth.service.impl;

import org.dozer.Mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpAuthorityDao;
import com.yjg.ec.platform.erp.service.auth.dao.ErpResouceDao;
import com.yjg.ec.platform.erp.service.auth.entity.ErpAuthorityEntity;
import com.yjg.ec.platform.erp.service.auth.entity.ErpResouceEntity;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErpResouceService {

	// private static Logger logger =
	// LoggerFactory.getLogger(ErpResouceService.class);

	@Resource
	private ErpResouceDao erpResouceDao;

	@Resource
	private ErpAuthorityDao erpAuthorityDao;

	@Resource
	private Mapper mapper;

	/**
	 * 根据主键id获取资源（菜单）信息
	 *
	 * @param id
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouce(Integer id) {
		List<ErpResouceEntity> entityList = erpResouceDao.queryErpResouce(id);
		List<ErpResouceResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpResouceResultDto dto = mapper.map(entity, ErpResouceResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
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
		List<ErpAuthorityEntity> entityList = erpAuthorityDao.queryErpAuthority(erpResouceParamDto.getAuthority_id());
		if (entityList.size() == 0) {
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
		List<ErpResouceEntity> entityList = erpResouceDao.queryErpResouceList(authority_id);
		List<ErpResouceResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {

		});
		return dtoList;
	}

	/**
	 * 根据用户id获取用户所有权限码集合
	 *
	 * @param userId
	 * @return
	 */
	public List<ErpResouceResultDto> getResouceByUserId(Integer userId) {
		List<ErpResouceEntity> entityList = erpResouceDao.getResouceByUserId(userId);
		List<ErpResouceResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpResouceResultDto dto = mapper.map(entity, ErpResouceResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 根据父id获取子菜单集合
	 *
	 * @param pid
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouceListByPid(Integer pid) {
		List<ErpResouceEntity> entityList = erpResouceDao.queryErpResouceListByPid(pid);
		List<ErpResouceResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpResouceResultDto dto = mapper.map(entity, ErpResouceResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * 根据条件获取菜单集合
	 *
	 * @param erpResouce
	 * @return
	 */
	public List<ErpResouceResultDto> queryErpResouceListByCondition(ErpResouceParamDto erpResouceParamDto) {
		List<ErpResouceEntity> entityList = erpResouceDao.queryErpResouceListByCondition(erpResouceParamDto);
		List<ErpResouceResultDto> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			ErpResouceResultDto dto = mapper.map(entity, ErpResouceResultDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
