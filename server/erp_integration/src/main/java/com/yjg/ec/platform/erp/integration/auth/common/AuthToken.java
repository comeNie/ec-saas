package com.yjg.ec.platform.erp.integration.auth.common;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class AuthToken implements RememberMeAuthenticationToken{

	private static final long serialVersionUID = -7409059963776995900L;

	private Object principal;
	private Object credentials;
	
	public AuthToken(Object principal, Object credentials){
		this.principal = principal;
		this.credentials = credentials;
	}
	
	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}

}
