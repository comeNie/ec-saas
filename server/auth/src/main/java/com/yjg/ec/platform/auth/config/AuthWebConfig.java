package com.yjg.ec.platform.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yjg.ec.platform.auth.interceptor.AuthInteceptor;

import javax.annotation.Resource;

/**
 * Created by zhangyunfei on 29/12/2016.
 */
@Configuration
public class AuthWebConfig extends WebMvcConfigurerAdapter {

	@Resource
	private AuthInteceptor authInteceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInteceptor).excludePathPatterns("/inner/**");
	}

}
