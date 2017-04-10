package com.yjg.ec.platform.erp.service.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnDeptParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnJobParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;
import com.yjg.ec.platform.erp.service.auth.dao.ErpUserDao;

import javax.annotation.Resource;

import java.util.List;

@Service
public class ErpUserService {

	private static Logger logger = LoggerFactory.getLogger(ErpUserService.class);

	@Resource
	private ErpUserDao erpUserDao;

	@Resource
	private ErpDeptService erpDeptService;

	@Resource
	private ErpJobService erpJobService;

	/**
	 * 根据用户id获取用户信息
	 */
	public List<ErpUserResultDto> queryErpUser(Integer id) {
		List<ErpUserResultDto> resList = erpUserDao.queryErpUser(id);
		return resList;
	}

	/**
	 * 根据登录名获取用户信息
	 *
	 * @param userName
	 * @return
	 */
	public ErpUserResultDto queryUserByLoginName(String userName) {
		return erpUserDao.queryUserByLoginName(userName);
	}

	/**
	 * 插入一条用户记录
	 *
	 * @param user
	 * @return
	 */
	@Transactional
	public Integer saveErpUser(ErpUserParamDto erpUserParamDto) {
		Integer i = erpUserDao.saveErpUser(erpUserParamDto);
		if (i > 0) {
			ErpUserResultDto userinfo = this.queryUserByLoginName(erpUserParamDto.getLogin_name());
			erpUserParamDto.setId(userinfo.getId());
			this.saveUserOnDept(erpUserParamDto);
			this.saveUserOnJob(erpUserParamDto);
		}
		return i;
	}

	/**
	 * 更新一条用户信息
	 *
	 * @param erpUser
	 * @return
	 */
	@Transactional
	public Integer updateErpUser(ErpUserParamDto erpUserParamDto) {
		this.saveUserOnDept(erpUserParamDto);
		this.saveUserOnJob(erpUserParamDto);
		return erpUserDao.updateErpUser(erpUserParamDto);
	}

	/**
	 * 查询所有可用用户集合
	 *
	 * @return
	 */
	public List<ErpUserResultDto> queryErpUserList(ErpUserParamDto erpUserParamDto) {
		return erpUserDao.queryErpUserList(erpUserParamDto);
	}

	private Integer saveUserOnDept(ErpUserParamDto erpUserParamDto) {
		Integer result = 0;
		ErpUserOnDeptParamDto uod = new ErpUserOnDeptParamDto();
		if (erpUserParamDto.getDept_id() == null) {
			uod.setUser_id(erpUserParamDto.getId());
			result = erpDeptService.deleteUserOnDept(uod);
		} else {
			uod.setUser_id(erpUserParamDto.getId());
			uod.setDept_id(erpUserParamDto.getDept_id());
			result = erpDeptService.saveUserOnDept(uod);
		}
		return result;

	}

	private Integer saveUserOnJob(ErpUserParamDto erpUserParamDto) {
		Integer result = 0;
		ErpUserOnJobParamDto uoj = new ErpUserOnJobParamDto();
		if (erpUserParamDto.getJob_id() == null) {
			uoj.setUser_id(erpUserParamDto.getId());
			result = erpJobService.deleteUserOnJob(uoj);
		} else {
			uoj.setUser_id(erpUserParamDto.getId());
			uoj.setJob_id(erpUserParamDto.getJob_id());
			result = erpJobService.saveUserOnJob(uoj);
		}
		return result;
	}

	public List<String> queryUserNames(String deptCode, String jobCode) {
		return erpUserDao.queryUserNames(deptCode, jobCode);
	}
}
