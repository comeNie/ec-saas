package com.yjg.ec.platform.erp.web.auth.controller;

import com.google.gson.Gson;
import com.yjg.ec.platform.erp.web.auth.common.Constants;
import com.yjg.ec.platform.erp.web.auth.exception.PageHintException;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.NestedServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BaseController {

	private final Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 统一的异常处理
	 *
	 * @param ex
	 * @param request
	 * @return
	 * @throws java.io.IOException
	 * @throws org.springframework.web.util.NestedServletException
	 * @throws net.sf.json.JSONException
	 */
	@ExceptionHandler(value = { Exception.class, PageHintException.class })
	public void actionExceptionHandler(Throwable ex, HttpServletRequest request, HttpServletResponse response)
			throws IOException, NestedServletException, JSONException {

		JSONObject jsonObject = null;

		String requestType = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(requestType);

		if (ex instanceof PageHintException) {
			Errors errors = ((PageHintException) ex).getErrors();
			StringBuffer errorMessage = new StringBuffer();
			if (errors != null && errors.hasErrors()) {
				List<ObjectError> oeList = errors.getAllErrors();
				for (ObjectError oe : oeList) {
					errorMessage.append(oe.getDefaultMessage()).append("</br>");
				}
				((PageHintException) ex).setErrorMessage(errorMessage.toString());
			} else {
				errorMessage.append(ex.getMessage());
			}

			if (!isAjax) {
				redirectErrorPage(errorMessage.toString(), request, response);
				logger.error(ex);
				return;
			}
			// VALIDATE_TYPE
			jsonObject = this.setFailResult(new JSONObject(), errorMessage.toString(), "validate");
		} else if (ex instanceof UnauthorizedException) {
			if (!isAjax) {
				redirectErrorPage(Constants.UNAUTHORIZED, request, response);
				logger.error(ex);
				return;
			}
			jsonObject = this.setFailResult(new JSONObject(), "网络异常，请稍后再试。");
			jsonObject.put(VALIDATE_TYPE, "exception");
		} else {
			if (!isAjax) {
				redirectErrorPage("网络异常，请稍后再试。", request, response);
				logger.error(ex);
				return;
			}
			jsonObject = this.setFailResult(new JSONObject(), "网络异常，请稍后再试。");
			jsonObject.put(VALIDATE_TYPE, "exception");
		}

		logger.error(ex);
		ex.printStackTrace();

		this.responseWrite(response, jsonObject);

	}

	private void redirectErrorPage(String message, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setAttribute("message", message);
		RequestDispatcher view = null;
		/*
		 * if (message.equals(Constants.UNAUTHORIZED)) { view =
		 * request.getRequestDispatcher("/jsp/auth/unauthorized.jsp"); } else {
		 * view = request.getRequestDispatcher("/jsp/error.jsp"); }
		 */
		view = request.getRequestDispatcher("/jsp/error.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 操作成功返回的json数据
	 *
	 * @param jsonObject
	 * @param message
	 * @return
	 * @throws net.sf.json.JSONException
	 */
	protected JSONObject setSuccessResult(JSONObject jsonObject, String message) throws JSONException {
		jsonObject.put(RESULT, SUCCESS);
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}

	protected JSONObject setSuccessResult(JSONObject jsonObject, String message, String paramMap) throws JSONException {
		jsonObject.put(RESULT, SUCCESS);
		jsonObject.put(MESSAGE, message);
		jsonObject.put("paramMap", paramMap);
		return jsonObject;
	}

	protected void responseJson(HttpServletResponse response, Object obj) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String jsonString = new Gson().toJson(obj);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
	}

	protected void responseWrite(HttpServletResponse response, JSONObject jsonObject)
			throws IOException, JSONException {
		final int jsonToString = 3;
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObject.toString(jsonToString));
	}

	protected void responseWrite(HttpServletResponse response, String message) throws IOException {

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(message);
	}

	protected void responseWrite(HttpServletResponse response, String message, int cacheTimesLong) throws IOException {
		Date date = new Date();
		response.setDateHeader("Last-Modified", date.getTime());
		response.setDateHeader("Expires", date.getTime() + cacheTimesLong);

		response.setHeader("Cache-Control", "public");
		response.setHeader("Pragma", "Pragma");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(message);
	}

	protected void responseWrite(HttpServletResponse response, JSONArray jsonObject) throws IOException, JSONException {
		final int jsonToString = 3;
		response.getWriter().write(jsonObject.toString(jsonToString));
	}

	/**
	 * 操作失败返回的json数据
	 *
	 * @param jsonObject
	 * @param message
	 * @return
	 * @throws net.sf.json.JSONException
	 */
	protected JSONObject setFailResult(JSONObject jsonObject, String message) throws JSONException {
		jsonObject.put(RESULT, "fail");
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}

	protected JSONObject setFailResult(JSONObject jsonObject, String message, String failType) throws JSONException {
		jsonObject.put(VALIDATE_TYPE, failType);
		jsonObject.put(RESULT, "fail");
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	/**
	 * 封装分页参数
	 *
	 * @param gb
	 */
	protected void paginationHandle(BaseGridVo gb) {
		if (gb.getLength() != 0) {
			if (gb.getRecords() == 0) {
				gb.setTotal(0);
				gb.setPage(0);
			} else {
				if (gb.getPage() > gb.getTotal()) {
					gb.setBegin(0);
					gb.setPage(1);
				} else {
					gb.setBegin(gb.getLength() * (gb.getPage() - 1));
				}
			}
		}
	}

	/**
	 * 封装分页参数
	 *
	 * @param gb
	 */
	protected void paginationHandleInf(BaseGridVo gb) {
		if (gb.getLength() == 0) {
			return;
		}
		gb.setBegin(gb.getLength() * (gb.getPage() - 1));
	}

	protected static final Integer DEFAULTP_AGESIZE = 10;
	protected static final String RESULT = "result";
	protected static final String VALIDATE_TYPE = "resultType";
	protected static final String SUCCESS = "success";
	protected static final String MESSAGE = "message";

}
