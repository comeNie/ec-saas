package com.yjg.ec.platform.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartCacheConfig extends CacheConfig {

	@Value("${saas.cache.expire_time.cart}")
	public Long getExpireTime() {
		return expireTime;
	}

	@Value("${saas.cache.prefix.cart}")
	public String getPrefix() {
		return prefix;
	}

}
