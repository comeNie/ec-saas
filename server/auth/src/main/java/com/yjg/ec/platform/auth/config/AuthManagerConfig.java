package com.yjg.ec.platform.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthManagerConfig {

	@Value("${saas.cache.expire_time.auth}")
	private Long expireTime;

	@Value("${saas.cache.prefix.auth}")
	private String prefix;

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
