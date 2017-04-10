package com.yjg.ec.platform.erp.service.auth.dao;

import com.yjg.ec.platform.erp.auth.param.dto.ErpUserOnJobParamDto;

/**
 * 
 * @author liubin
 *
 */
@MyBatisRepository
public interface ErpUserOnJobDao {

	Integer deleteUserOnJob(ErpUserOnJobParamDto erpUserOnJobParamDto);

	Integer saveUserOnJob(ErpUserOnJobParamDto erpUserOnJobParamDto);

	Integer updateUserOnJob(ErpUserOnJobParamDto erpUserOnJobParamDto);

	Integer countUserNumByJob(Integer jobId);

}
