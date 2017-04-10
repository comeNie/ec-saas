package com.yjg.ec.platform.erp.web.auth.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.auth.api.ErpResouceApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceSessionParamDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gus on 2015/8/31.
 */
@Controller
@RequestMapping("/frame")
public class FrameController extends BaseController {

	@Resource
	public ErpResouceApi erpResouceApi;

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/top")
	public ModelAndView top(HttpServletRequest request) throws Exception {
		ModelMap modelMap = new ModelMap();
		List<ErpResouceSessionParamDto> tree = (List<ErpResouceSessionParamDto>) SecurityUtils.getSubject()
				.getSession(false).getAttribute("resourceTree");
		modelMap.put("tree", tree);
		return new ModelAndView("top", modelMap);
	}

	@SuppressWarnings("unused")
	private JSONObject parseJson(ErpResouceParamDto resouce) throws Exception {
		if (resouce != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", resouce.getName());
			jsonObject.put("url", resouce.getPath());
			jsonObject.put("pid", resouce.getPid());
			return jsonObject;
		}
		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/right")
	public ModelAndView right(HttpServletRequest request) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("right", modelMap);
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/footer")
	public ModelAndView footer(HttpServletRequest request) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("footer", modelMap);
	}

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/left")
	public ModelAndView left(HttpServletRequest request) throws Exception {
		ModelMap modelMap = new ModelMap();
		String pid = request.getParameter("pid");
		if (StringUtils.isNotEmpty(pid)) {
			List<ErpResouceSessionParamDto> tree = (List<ErpResouceSessionParamDto>) SecurityUtils.getSubject()
					.getSession(false).getAttribute("resourceTree");
			for (ErpResouceSessionParamDto session : tree) {
				if (Integer.parseInt(pid) == session.getId()) {
					JSONArray jsonArray = JSONArray.fromObject(session);
					modelMap.put("data", jsonArray.toString());
					modelMap.put("data_name", session.getName());
				}
			}
		}
		return new ModelAndView("left", modelMap);
	}
}
