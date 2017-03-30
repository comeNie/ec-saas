package com.yjg.ec.platform.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhangyunfei on 29/12/2016.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
//    @Value("${cors_domain}")
//    private String corsDomain;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new AvoidDuplicateSubmitInterceptor()).excludePathPatterns("/inner/**");
    }

    //多个域名跨域有问题，解决后可以采用该方式。path不能排除也需要�?�虑
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        CorsRegistration corsRegistration = registry.addMapping("/**");
//        corsRegistration.allowCredentials(true);
//        corsRegistration.allowedHeaders("Content-Type");
//        String[] corsDomains = StringUtils.split(corsDomain, ",");
//
//        if (ArrayUtils.isNotEmpty(corsDomains)) {
//            corsRegistration.allowedOrigins(corsDomains);
//        }
//
//    }
}
