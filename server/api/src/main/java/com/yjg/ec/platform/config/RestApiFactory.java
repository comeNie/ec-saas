package com.yjg.ec.platform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.yjg.ec.platform.common.exception.BusinessException;

/**
 * Created by zhangyunfei on 25/12/2016.
 */
public class RestApiFactory<T> implements InitializingBean, FactoryBean<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiFactory.class);

    private String innerClassName;

    public RestApiProxy getRestApiProxy() {
        return restApiProxy;
    }

    public void setRestApiProxy(RestApiProxy restApiProxy) {
        this.restApiProxy = restApiProxy;
    }

    private RestApiProxy restApiProxy;


    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isInterface()) {
            return (T) RestApiProxy.newInstance(innerClass, restApiProxy);
        } else {
            throw new BusinessException("Not Support not interface");
        }
    }

    public Class<?> getObjectType() {
        if (innerClassName == null) {
            return null;
        }
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
    }
}