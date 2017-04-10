package com.yjg.ec.platform.erp.integration.auth.filter;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceSessionParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.AuthToken;
import com.yjg.ec.platform.erp.integration.auth.service.AuthService;
import com.yjg.ec.platform.erp.integration.auth.util.AuthUtils;
import com.yjg.ec.platform.erp.integration.auth.web.ShiroUser;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AuthFilter extends AccessControlFilter {

	private static final Logger logger = Logger.getLogger(AuthFilter.class);

	private String cookieName;

	@Resource
	private AuthService authService;

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		if (subject.isAuthenticated()) {
			return true;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		try {
			return executeLogin(req);
		} catch (AuthenticationException e) {
			req.setAttribute("shiroLoginFailure", e.getClass().getSimpleName());
			logger.error("用户登录异常", e);
			return false;
		} catch (Exception e) {
			req.setAttribute("shiroLoginFailure", e.getClass().getSimpleName());
			logger.error("调用权限服务获取用户信息发生异常", e);
			return false;
		}
	}

	private boolean executeLogin(HttpServletRequest request) throws Exception {
		boolean loginSuccessfully = false;
		ShiroUser shiroUser = createPrincipal(request);
		if (shiroUser != null) {
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new AuthToken(shiroUser, shiroUser.getAccount());
			subject.login(token);
			// 如果是代理登录则去掉URL中的ticket参数,不在浏览器上显示ticket参数
			// redirectForAgent(request, response);
			loginSuccessfully = true;
			subject.getSession(false).setAttribute("resourceTree", createNodeTree(shiroUser.getId()));
		}
		return loginSuccessfully;
	}

	private ShiroUser createPrincipal(HttpServletRequest request) {
		String loginName = request.getParameter("username");
		String password = request.getParameter("password");

		if ((StringUtils.hasText(loginName) && StringUtils.hasText(password))
				&& authService.authenticate(loginName, password)) {
			ErpUserResultDto erpUser = authService.getUserByLoginName(loginName);
			ShiroUser shiroUser = new ShiroUser();
			if (erpUser != null) {
				shiroUser.setId(erpUser.getId());
				shiroUser.setAccount(loginName);
				return shiroUser;
			} else {
				ErpUserParamDto erpUserParamDto = new ErpUserParamDto();
				erpUserParamDto.setLogin_name(loginName);
				erpUserParamDto.setCreate_time(new Date());
				erpUserParamDto.setSystem_id(0);
				erpUserParamDto.setIsAlive(1);
				Integer id = authService.saveUser(erpUserParamDto);
				shiroUser.setId(erpUser.getId());
				shiroUser.setAccount(loginName);
				return shiroUser;
			}
		}
		return null;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (AuthUtils.isAjaxRequest(httpServletRequest)) {
			AuthUtils.ajaxLoginResponse(httpServletResponse);
		} else {
			saveRequest(request);
			String loginUrl = getLoginUrl();
			Map<String, String> map = new HashMap<String, String>();
			map.put("shiroLoginFailure", (String) httpServletRequest.getAttribute("shiroLoginFailure"));
			WebUtils.issueRedirect(request, response, loginUrl, map);
		}
		return false;
	}

	// 创建菜单树(修改原来频繁访问数据库问题)
	public List<ErpResouceSessionParamDto> createNodeTree(Integer userId) {
		List<ErpResouceSessionParamDto> erpResouceSessionList = new ArrayList<>();
		List<ErpResouceResultDto> erpResouceList = authService.getResouceByUserId(userId);
		for (ErpResouceResultDto erpResouceResultDto : erpResouceList) {
			if (erpResouceResultDto.getPid() == 0) {
				ErpResouceSessionParamDto erpResouceSession = new ErpResouceSessionParamDto();
				erpResouceSession.setId(erpResouceResultDto.getId());
				erpResouceSession.setPid(0);
				erpResouceSession.setName(erpResouceResultDto.getName());
				erpResouceSession.setUrl(erpResouceResultDto.getPath());
				erpResouceSession = createNode(erpResouceList, erpResouceSession, erpResouceResultDto.getId());
				erpResouceSessionList.add(erpResouceSession);
			}
		}
		return erpResouceSessionList;
	}

	// 递归创建单棵树
	private static ErpResouceSessionParamDto createNode(List<ErpResouceResultDto> erpResouceList,
			ErpResouceSessionParamDto erpResouceSession, Integer pid) {
		for (ErpResouceResultDto erpResouceResultDto : erpResouceList) {
			if (erpResouceResultDto.getPid() == pid) {
				ErpResouceSessionParamDto erpSession = new ErpResouceSessionParamDto();
				erpSession.setId(erpResouceResultDto.getId());
				erpSession.setPid(erpResouceResultDto.getPid());
				erpSession.setName(erpResouceResultDto.getName());
				erpSession.setUrl(erpResouceResultDto.getPath());
				erpResouceSession.getChildren().add(erpSession);
				createNode(erpResouceList, erpSession, erpResouceResultDto.getId());
			}
		}
		return erpResouceSession;
	}

}