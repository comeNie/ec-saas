package com.yjg.ec.platform.erp.auth.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.erp.auth.param.dto.AuthLdapParamDto;

/**
 * Created by gus on 2015/8/28.
 */
@RestApi
public interface AuthLdapApi {

	/**
	 * 根据用户名密码验证
	 *
	 * @param authLdapDto
	 * @return
	 */
	@RequestMapping("/authenticate")
	public boolean authenticate(AuthLdapParamDto authLdapParamDto) throws Exception;

}
