package com.yjg.ec.platform.erp.service.auth.service;

/**
 * Created by gus on 2015/8/31.
 */
public interface AuthLdapService {

	/**
	 * 根据sAMAccountName(名字全拼)属性取得用户DN（当然你可以根据自己情况换成别的属性来操作）
	 *
	 * @param cn
	 * @return
	 */
	public String getDnForUser(String cn);

	/**
	 * 根据用户名密码验证
	 *
	 * @param userCn
	 * @param credentials
	 * @return
	 */
	public boolean authenticate(String userCn, String credentials) throws Exception;
}
