package com.yjg.ec.platform.config;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjg.ec.platform.common.exception.BusinessException;

/**
 * Created by zhangyunfei on 18/12/2016.
 */
@Component
public class RestClient extends RestTemplate{

    @Resource
    private PasswordEncoder tokenPasswordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    public RestClient() {
        super.getMessageConverters().forEach(converter -> {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(Charset.forName("UTF-8"));
            }
        });
        List<ClientHttpRequestInterceptor> list = new ArrayList<>();
        list.add(
                (request, body, execution)-> {
                    URI uri = request.getURI();
                    String encryption = tokenPasswordEncoder.encode(uri.toString());
                    HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
                    requestWrapper.getHeaders().set("InnerApiToken", encryption);
                    requestWrapper.getHeaders().set("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
                    return execution.execute(requestWrapper, body);
                }
        );
        this.setInterceptors(list);
    }

    @Resource
    public void setRestUriTemplateHandler(RestUriTemplateHandler restUriTemplateHandler) {
        this.setUriTemplateHandler(restUriTemplateHandler);
    }

    public Object rpcCall(Method method, Object[] args) throws IOException {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

        if (requestMapping == null) {
            throw new BusinessException("this method not a rest api client");
        }
        String[] paths = requestMapping.path();

        if (ArrayUtils.isEmpty(paths)) {
            paths = requestMapping.value();
        }
        if (ArrayUtils.isEmpty(paths)) {
            throw new BusinessException("request path is nessesary");
        }
        RequestMethod[] requestMethods = requestMapping.method();

        Type returnType = method.getGenericReturnType();

        Object result = null;

        Object requestBody = getRequestBody(method, args);
        Object[] uriVariable = getPathVariable(method, args);

        RequestMethod requestMethod = parseMethod(requestMethods);

        if (returnType == null || returnType == Void.TYPE) {
            if (requestMethod == RequestMethod.POST) {
                result = this.postForObject(paths[0], requestBody, Object.class, uriVariable);
            }
            if (requestMethod == RequestMethod.GET) {
                result = this.getForObject(paths[0], Object.class, uriVariable);
            }
        } else {
            JavaType javaType = objectMapper.getTypeFactory().constructType(returnType);

            String string = null;

            if (requestMethod == RequestMethod.POST) {
                string = this.postForObject(paths[0], requestBody, String.class, uriVariable);
            }
            if (requestMethod == RequestMethod.GET) {
                string = this.getForObject(paths[0], String.class, uriVariable);
            }

            result = string==null?null:objectMapper.readValue(string, javaType);
        }

        return result;
    }

    private RequestMethod parseMethod(RequestMethod[] requestMethods) {
        RequestMethod requestMethod = null;
        if (ArrayUtils.isEmpty(requestMethods)) {
            requestMethod = RequestMethod.POST;
        }
        List list = Arrays.asList(requestMethods);

        if (list.contains(RequestMethod.GET)) {
            requestMethod = RequestMethod.GET;
        }
        if (list.contains(RequestMethod.POST)) {
            requestMethod = RequestMethod.POST;
        }
        if (requestMethod == null) {
            throw new BusinessException("Unsupport HEAD, PUT, PATCH, DELETE, OPTIONS, TRACE");
        }

        return requestMethod;
    }

    private Object getRequestBody(Method method, Object[] args) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        if (ArrayUtils.isEmpty(parameterAnnotations)) {
            return null;
        }
        if (parameterAnnotations.length != args.length) {
            throw new BusinessException("invalid call!");
        }

        for (int i = 0; i < parameterAnnotations.length; i++) {
            if(!ArrayUtils.isEmpty(parameterAnnotations[i])) {
                for (int j = 0; j < parameterAnnotations[i].length; j++) {
                    if (parameterAnnotations[i][j] instanceof RequestBody) {
                        return args[i];
                    }
                }
            }
        }
        return null;
    }

    private Object[] getPathVariable(Method method, Object[] args) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        if (ArrayUtils.isEmpty(parameterAnnotations)) {
            return null;
        }
        if (parameterAnnotations.length != args.length) {
            throw new BusinessException("invalid call!");
        }

        List list = new ArrayList();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            if (!ArrayUtils.isEmpty(parameterAnnotations[i])) {
                for (int j = 0; j < parameterAnnotations[i].length; j++) {
                    if (parameterAnnotations[i][j] instanceof PathVariable) {
                        PathVariable pv = (PathVariable) parameterAnnotations[i][j];
                        String name = StringUtils.isBlank(pv.value())
                                ? pv.name() : pv.value();
                        list.add(args[i]);
                    }
                }
            }
        }
        return list.toArray();
    }

    public <T> T post(String path, Object object, TypeReference<T> typeReference) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeReference);
        String string = this.postForObject(path, object, String.class);
        return objectMapper.readValue(string, javaType);
    }

    public <T> T post(String path, Object object, TypeReference<T> typeReference, Object... objs) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeReference);
        String string = this.postForObject(path, object, String.class, objs);
        return objectMapper.readValue(string, javaType);
    }

    public <T> T post(String path, Object object, TypeReference<T> typeReference, Map<String, Object> map) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeReference);
        String string = this.postForObject(path, object, String.class, map);
        return objectMapper.readValue(string, javaType);
    }

    public <T> T get(String path, TypeReference<T> typeReference, Object... objs) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeReference);
        String string = this.getForObject(path, String.class, objs);
        return objectMapper.readValue(string, javaType);
    }

    public <T> T get(String path, TypeReference<T> typeReference, Map<String, Object> map) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeReference);
        String string = this.getForObject(path, String.class, map);
        return objectMapper.readValue(string, javaType);
    }

}