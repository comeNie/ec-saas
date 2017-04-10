package com.yjg.ec.platform.erp.service.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import com.yjg.ec.platform.erp.service.auth.service.AuthLdapService;

import javax.annotation.Resource;
import javax.naming.directory.DirContext;
import java.util.List;

/**
 * Created by gus on 2015/8/31.
 */
@Service
public class AuthLdapServiceImpl implements AuthLdapService {

	private static final Logger logger = Logger.getLogger(AuthLdapServiceImpl.class);

	@Resource
	private LdapTemplate ldapTemplate;

	/**
	 * 根据sAMAccountName(名字全拼)属性取得用户DN（当然你可以根据自己情况换成别的属性来操作）
	 *
	 * @param cn
	 * @return
	 */
	public String getDnForUser(String cn) {
		EqualsFilter f = new EqualsFilter("sAMAccountName", cn);
		List result = ldapTemplate.search("", f.toString(), new AbstractContextMapper() {
			protected Object doMapFromContext(DirContextOperations ctx) {
				return ctx.getNameInNamespace();
			}
		});
		if (result.size() != 1) {
			throw new RuntimeException("User not found or not unique");
		}
		return (String) result.get(0);
	}

	/**
	 * 根据用户名密码验证
	 *
	 * @param userCn
	 * @param credentials
	 * @return
	 */
	public boolean authenticate(String userCn, String credentials) throws Exception {
		DirContext ctx = null;
		try {
			ctx = ldapTemplate.getContextSource().getContext(getDnForUser(userCn), credentials);
			return true;
		} catch (Exception e) {
			logger.error("根据用户名密码验证信息发生异常", e);
			throw new RuntimeException("根据用户名密码验证信息发生异常");
		}
	}
}
