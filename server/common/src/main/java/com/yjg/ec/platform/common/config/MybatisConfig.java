package com.yjg.ec.platform.common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfig {
    
    @Bean
    public PageHelper pageHelper() {
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        /*设置为true时，如果pageSize=0或�?�RowBounds.limit = 0就会查询出全部的结果
          相当于没有执行分页查询，但是返回结果仍然是Page类型*/
        properties.setProperty("pageSizeZero", "true");
        /*3.3.0版本可用 - 分页参数合理化，默认false禁用
          启用合理化时，如果pageNum<1会查询第�?页，如果pageNum>pages会查询最后一�?
          禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据*/
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        /*3.5.0版本可用 - 为了支持startPage(Object params)方法
          增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取�?
          可以配置pageNum,pageSize,count,pageSizeZero,reasonable,不配置映射的用默认�??*/
        properties.setProperty("params", "count=countSql");
        /*该参数默认为false
          设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
          和startPage中的pageNum效果�?�?*/
        properties.setProperty("offsetAsPageNum", "true");
        /*该参数默认为false
          设置为true时，使用RowBounds分页会进行count查询*/
        properties.setProperty("rowBoundsWithCount", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
    
    @Bean
    public ServletRegistrationBean duridServletRegistrationBean(){
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(servlet);
        registration.setEnabled(true);
        registration.addUrlMappings("/druid/*");
        return registration;
    }
}