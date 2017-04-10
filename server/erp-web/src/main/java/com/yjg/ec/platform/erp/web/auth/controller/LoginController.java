package com.yjg.ec.platform.erp.web.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gus on 2015/8/31.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

	/**
	 * 初始化登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView loginGet(HttpServletRequest request) throws Exception {
		ModelMap modelMap = new ModelMap();
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		String authExp = (String) request.getParameter("shiroLoginFailure");
		if (StringUtils.isNotEmpty(authExp)) {
			String expMsg = "";
			if (authExp.indexOf(UnknownAccountException.class.getSimpleName()) > -1
					|| authExp.indexOf(IncorrectCredentialsException.class.getSimpleName()) > -1) {
				expMsg = "用户账号或密码错误";
			} else {
				expMsg = "登录异常,请重试";
			}
			modelMap.addAttribute("error", expMsg);
		}
		return new ModelAndView("auth/login", modelMap);
	}

	/**
	 * 初始化登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/loginCheck")
	public String loginPost(HttpServletRequest request) throws Exception {
		return "redirect:index";
	}

	/**
	 * 进入没有权限页面
	 *
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() throws Exception {
		return "auth/unauthorized";
	}

	/**
	 * 初始化首页
	 *
	 * @return
	 */
	@RequestMapping("/index")
	public String index() throws Exception {
		return "index";
	}

	/**
	 * 退出
	 *
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() throws Exception {
		return "auth/login";
	}
}
