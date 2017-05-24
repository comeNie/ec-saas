package com.yjg.ec.platform.cache.config;

public abstract class CacheConfig {

	protected Long expireTime;

	protected String prefix;

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
