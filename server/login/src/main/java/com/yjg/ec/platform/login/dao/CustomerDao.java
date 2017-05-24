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

	public CustomerInfo getCustomerInfoById(Long customerId);

	public List<CustomerInfo> getCustomertInfoByIds(List<Long> customerIds);

	public CustomerInfo getCustomerInfoByLoginName(String loginName);

	public int updateCustomer(CustomerInfo customer);

	public int initCustomer(Long customerId);
}
