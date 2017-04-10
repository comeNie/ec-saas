package com.yjg.ec.platform.erp.service.auth.dao;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyBatisRepository {

    String value() default "";
}