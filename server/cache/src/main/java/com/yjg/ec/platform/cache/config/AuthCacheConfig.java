package com.yjg.ec.platform.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthCacheConfig extends CacheConfig {

	@Value("${saas.cache.expire_time.auth}")
	public Long getExpireTime() {
		return expireTime;
	}

	@Value("${saas.cache.prefix.auth}")
	public String getPrefix() {
		return prefix;
	}

}
