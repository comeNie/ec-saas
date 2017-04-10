package com.yjg.ec.platform.erp.web.auth.exception;

import org.apache.log4j.Logger;

/**
 * @ClassName: BaseException
 * @Description:
 * @author 张超
 * 
 */
public class BaseWebException extends Exception {

	private static final long serialVersionUID = 807477419235941651L;

	protected final Logger log = Logger.getLogger(BaseWebException.class);

	private String errorMessage;

	public BaseWebException() {
		super();
	}

	public BaseWebException(String message) {
		super(message);
	}

	public BaseWebException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
