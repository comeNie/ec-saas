package com.yjg.ec.platform.security.dao;

import com.nrb.maledisease.security.domain.DoctorLoginDeviceInfoDomain;
import com.nrb.maledisease.security.dto.DoctorSecurityCheckParamDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mengzipeng on 2017/3/24.
 */
@Component
@Mapper
public interface SecurityDao {

    int insert(LoginDeviceInfoDomain doctorLoginDeviceInfoDomain);

    List<LoginDeviceInfoDomain> getDeviceListByLoginName(String doctorLoginName);

    int countDeviceByLoginName(DoctorSecurityCheckParamDto paramDto);

    LoginDeviceInfoDomain getDeviceInfoById(Long id);
}
