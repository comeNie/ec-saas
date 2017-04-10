package com.yjg.ec.platform.erp.integration.auth.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class AuthCredentialsMatcher implements CredentialsMatcher{
	@Override
	public boolean doCredentialsMatch(AuthenticationToken arg0, AuthenticationInfo arg1) {
		return true;
	}
}
