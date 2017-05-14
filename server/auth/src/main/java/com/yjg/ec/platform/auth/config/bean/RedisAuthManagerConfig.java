package com.yjg.ec.platform.auth.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yjg.ec.platform.auth.api.AuthManager;

/**
 * Created by zhangyunfei on 12/02/2017.
 */
@Configuration
public class RedisAuthManagerConfig {

	@Bean
	public AuthManager authManager() {
		return new RedisAuthManager();
	}

}
