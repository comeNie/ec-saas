package com.yjg.ec.platform.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by zhangyunfei on 25/12/2016.
 */
public class RestApiProxy implements InvocationHandler {

    private static RestClient restClient;

    private static ApplicationContext applicationContext;

    private static RestApiProxy restApiProxy = new RestApiProxy();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (restClient == null) {
            restClient = applicationContext.getBean(RestClient.class);
        }
        return restClient.rpcCall(method, args);
    }

    public static <T> T newInstance(Class<T> innerInterface, RestApiProxy restApiProxy) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        return (T) Proxy.newProxyInstance(classLoader, interfaces, restApiProxy);
    }

    public static RestApiProxy getInstance(ApplicationContext applicationContext) {
        if (RestApiProxy.applicationContext == null) {
            RestApiProxy.applicationContext = applicationContext;
        }
        return restApiProxy;
    }
}