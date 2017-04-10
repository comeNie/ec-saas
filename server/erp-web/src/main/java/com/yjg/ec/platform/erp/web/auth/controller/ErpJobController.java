package com.yjg.ec.platform.erp.web.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.web.auth.handler.ErpJobHandler;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpJobVo;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liubin
 */
@Controller
@RequestMapping("/job")
public class ErpJobController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ErpJobController.class);

	@Resource
	public ErpJobHandler erpJobHandler;

	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public ModelAndView listIndex() throws Exception {
		return new ModelAndView("job/jobList");
	}

	@RequestMapping("/query")
	@RequiresPermissions("ERP_AUTH_TITLE")
	@ResponseBody
	public BaseGridVo queryList() throws Exception {
		BaseGridVo gridVo = new BaseGridVo();
		// paginationHandle(gridVo);
		List<ErpJobVo> list = erpJobHandler.queryJobList();
		gridVo.setJsonList(list);
		gridVo.setRecords(list.size());
		return gridVo;
	}

	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public String addNew() throws Exception {
		return "job/jobEdit";
	}

	@RequestMapping("/detail")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public ModelAndView detail(String jobId) throws Exception {
		ModelAndView mv = new ModelAndView("job/jobEdit");
		mv.addObject("erpJob", erpJobHandler.getJob(jobId));
		return mv;
	}

	@RequestMapping("/saveOrUpdate")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public String saveOrUpdate(ErpJobVo jobVo) throws Exception {
		erpJobHandler.saveOrUpdate(jobVo);
		return "redirect:/job/list";
	}

	@RequestMapping("/delete")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public String delete(String jobId) throws Exception {
		erpJobHandler.deleteJob(jobId);
		return "redirect:/job/list";
	}

	@RequestMapping("/requestJobs")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public void ajaxJobs(HttpServletResponse response) throws Exception {
		responseJson(response, erpJobHandler.queryJobList());
	}

	@RequestMapping("/checkIsRepeat")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public void ajaxCheckJobIsRepeat(String id, String code, HttpServletResponse response) throws Exception {
		responseJson(response, erpJobHandler.checkIsRepeat(id, code));
	}

	@RequestMapping("/countUsers")
	@RequiresPermissions("ERP_AUTH_TITLE")
	public void countUsers(String jobId, HttpServletResponse response) throws Exception {
		responseJson(response, erpJobHandler.countUsersByJob(jobId));
	}

}
