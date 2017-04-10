package com.yjg.ec.platform.erp.service.auth.api;

import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.auth.api.AuthLdapApi;
import com.yjg.ec.platform.erp.auth.param.dto.AuthLdapParamDto;
import com.yjg.ec.platform.erp.service.auth.service.AuthLdapService;

import javax.annotation.Resource;

/**
 * @author Mengzipeng
 * @date 2015/9/21
 */
@Service
public class AuthLdapApiImpl implements AuthLdapApi {

	@Resource
	private AuthLdapService authLdapService;

	@Override
	public boolean authenticate(AuthLdapParamDto authLdapParamDto) throws Exception {
		return authLdapService.authenticate(authLdapParamDto.getUserCn(), authLdapParamDto.getCredentials());
	}
}
