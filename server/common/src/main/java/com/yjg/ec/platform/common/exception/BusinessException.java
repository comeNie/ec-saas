package com.yjg.ec.platform.common.exception;

/**
 * Created by zhangyunfei on 27/11/2016.
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException() {
		super();
	}
}
