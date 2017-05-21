package com.yjg.ec.platform.login.handle.impl;

import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import com.yjg.ec.platform.auth.config.AuthConfig;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by zhangyunfei on 19/01/2017.
 */
@Component
public class CustomerAuthConfig {

    @Resource
    private AuthConfig authConfig;

    @Resource
    private CustomerLoginRealm patientLoginRealm;

    @Resource
    private CustomerLoginSuccessHandle patientLoginSuccessHandle;

    @Resource
    private CustomerOpenIdRealm patientOpenIdRealm;

    @PostConstruct
    public void config() {
        authConfig.registerLoginRealm("PATIENT", patientLoginRealm);
        authConfig.registerOpenIdLoginRealm("PATIENT", patientOpenIdRealm);
        authConfig.registerLoginSuccessHandle("PATIENT", patientLoginSuccessHandle);
        authConfig.registerPasswordEncoder("PATIENT", new StandardPasswordEncoder());
    }
}
