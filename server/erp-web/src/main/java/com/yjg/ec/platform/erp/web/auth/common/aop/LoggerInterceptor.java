package com.yjg.ec.platform.erp.web.auth.common.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 统一日志获取参数及传入参数
 */
@Aspect
@Component
public class LoggerInterceptor {

	@Around("pointcutController()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		final Signature signature = joinPoint.getSignature();
		Class declaringType = signature.getDeclaringType();

		final Logger logger = LoggerFactory.getLogger(declaringType);

		Object object = null;
		String args = ToStringBuilder.reflectionToString(joinPoint.getArgs());

		String className = declaringType.getName();
		String methodName = signature.getName();

		try {
			object = joinPoint.proceed();
			logger.info(String.format("调用信息：[%s:%s]，\n入参：[%s],结果：[%s]", className, methodName, args, object));
		} catch (RuntimeException e) {
			logger.error(String.format("调用信息：[%s:%s]，\n入参：[%s]，异常：[%s]", className, methodName, args, e));
			throw e;
		}
		return object;
	}

	/**
	 * controller层的拦截表达式
	 */
	@Pointcut("execution(public * cn.gusmedsci.erp.auth.controller..*.*(..)))")
	public void pointcutController() {

	}

}
