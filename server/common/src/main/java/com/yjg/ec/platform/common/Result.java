package com.yjg.ec.platform.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yjg.ec.platform.common.exception.BusinessException;
import com.yjg.ec.platform.common.exception.ParamException;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyunfei on 27/11/2016.
 */
public class Result<T> {

	private static final int BUSINESSERROR = 520;

	public static final String APP_ERROR = "系统异常，请稍后再试";

	private int code;
	private String message;
	private Map<String, Object> extraMap = new HashMap<String, Object>();
	private T resultData;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getExtraMap() {
		return extraMap;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	public void addExtraMap(String key, Object value) {
		extraMap.put(key, value);
	}

	@SuppressWarnings("rawtypes")
	public static Result buildExceptionResult(Exception e) {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		result.setMessage(e.getMessage());
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildExceptionResult() {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		result.setMessage(APP_ERROR);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildExceptionResult(String message) {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		result.setMessage(message);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildAuthExceptionResult(String message) {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_UNAUTHORIZED);
		result.setMessage(message);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildInvalidParamResult(ParamException e) {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_BAD_REQUEST);
		result.setMessage(e.getMessage());
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildInvalidParamResult(String message) {
		Result<Object> result = new Result<>();
		result.setCode(HttpServletResponse.SC_BAD_REQUEST);
		result.setMessage(message);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildBusinessErrorResult(BusinessException e) {
		Result<Object> result = new Result<>();
		result.setCode(Result.BUSINESSERROR);
		result.setMessage(e.getMessage());
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildBusinessErrorResult(String message) {
		Result<Object> result = new Result<>();
		result.setCode(Result.BUSINESSERROR);
		result.setMessage(message);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result buildSuccessResult(String message) {
		Result<Object> result = new Result<>();
		result.setMessage(message);
		result.setCode(HttpServletResponse.SC_OK);
		return result;
	}

	public static <T> Result<T> buildSuccessResult(T t) {
		Result<T> result = new Result<T>();
		result.setMessage("");
		result.setCode(HttpServletResponse.SC_OK);
		result.setResultData(t);
		return result;
	}

	public static <T> Result<T> buildSuccessResult(String message, T t) {
		Result<T> result = new Result<>();
		result.setMessage(message);
		result.setCode(HttpServletResponse.SC_OK);
		result.setResultData(t);
		return result;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return HttpServletResponse.SC_OK == code;
	}
}
