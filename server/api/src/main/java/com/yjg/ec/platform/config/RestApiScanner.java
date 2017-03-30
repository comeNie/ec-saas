package com.yjg.ec.platform.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.yjg.ec.platform.annotation.RestApi;

/**
 * Created by zhangyunfei on 25/12/2016.
 */
public class RestApiScanner extends ClassPathBeanDefinitionScanner {

    public RestApiScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(RestApi.class));
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions =   super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            RestApiProxy restApiProxy = RestApiProxy.getInstance((ApplicationContext) this.getResourceLoader());
            definition.getPropertyValues().add("restApiProxy", restApiProxy);
            definition.setBeanClass(RestApiFactory.class);
        }
        return beanDefinitions;
    }

    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent() && beanDefinition.getMetadata()
                .hasAnnotation(RestApi.class.getName());
    }
}