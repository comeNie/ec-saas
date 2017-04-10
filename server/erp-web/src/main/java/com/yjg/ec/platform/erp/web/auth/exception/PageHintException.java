package com.yjg.ec.platform.erp.web.auth.exception;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

/**
 * @ClassName: FrontValidationException
 * @Description:
 * @author 张超
 * 
 */
public class PageHintException extends BaseWebException {

	private static final long serialVersionUID = -125073506113127808L;
	protected final Logger log = Logger.getLogger(PageHintException.class);

	/**
	 * 整合spring mvc校验功能 统一处理
	 * */
	private Errors errors;

	public static final String FRONT_MESSAGE_EXCEPTION = "前端数据校验异常.";

	public Errors getErrors() {
		return errors;
	}

	public PageHintException() {
		super();
	}

	public PageHintException(String errorMessage) {
		super(errorMessage);
		this.setErrorMessage(errorMessage);
	}

	public PageHintException(Exception e) {
		super(e.getMessage(), e);
	}

	public PageHintException(int errorCode, Errors errors) {
		super(FRONT_MESSAGE_EXCEPTION);
		this.errors = errors;
	}

	public PageHintException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.setErrorMessage(errorMessage);
	}
}
