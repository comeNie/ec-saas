package com.yjg.ec.platform.login.dao;

import com.yjg.ec.platform.auth.dto.LoginUser;
import com.yjg.ec.platform.customer.param.dto.CustomerUserParamDto;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyunfei on 02/12/2016.
 */
@Mapper
@Component
public interface CustomerUserDao {

	public long addCustomer(CustomerUserParamDto customerUserParamDto);

	public LoginUser getCustomerLogin(String patientName);

	public LoginUser getCustomerLoginByWechatOpenId(String openId);
}
