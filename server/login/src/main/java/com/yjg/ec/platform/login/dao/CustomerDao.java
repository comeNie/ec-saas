package com.yjg.ec.platform.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.login.domain.CustomerInfo;

import java.util.List;

/**
 * Created by zhangyunfei on 13/12/2016.
 */
@Mapper
@Component
public interface CustomerDao {

	public CustomerInfo getPatientInfoById(Long patientId);

	public List<CustomerInfo> getPatientInfoByIds(List<Long> patientIds);

	public CustomerInfo getPatientInfoByLoginName(String loginName);

	public int updatePatient(CustomerInfo patient);

	public int initPatient(Long patientId);
}
