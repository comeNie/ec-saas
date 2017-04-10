package com.yjg.ec.platform.erp.service.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjg.ec.platform.erp.auth.param.dto.ErpJobParamDto;
import com.yjg.ec.platform.erp.service.auth.entity.ErpJobEntity;

/**
 * 
 * @author liubin
 *
 */
@MyBatisRepository
public interface ErpJobDao {

	ErpJobEntity queryJob(@Param(value = "id") Integer id);

	Integer saveJob(ErpJobParamDto erpJobParamDto);

	List<ErpJobEntity> queryJobList();

	Integer updateJob(ErpJobParamDto erpJobParamDto);

	Integer deleteJob(@Param(value = "id") Integer id);

	Integer softDeleteJob(ErpJobParamDto erpJobParamDto);

	Integer countJobByCode(ErpJobParamDto erpJobParamDto);
}
