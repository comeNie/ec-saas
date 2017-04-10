package com.yjg.ec.platform.erp.web.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.web.auth.handler.ErpDeptHandler;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpDeptVo;

/**
 * @author liubin
 */
@Controller
@RequestMapping("/dept")
public class ErpDeptController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ErpDeptController.class);

	@Resource
	public ErpDeptHandler erpDeptHandler;

	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public ModelAndView listIndex() throws Exception {
		return new ModelAndView("deptment/deptList");
	}

	@RequestMapping("/query")
	@RequiresPermissions("ERP_AUTH_DEPT")
	@ResponseBody
	public BaseGridVo queryList() throws Exception {
		BaseGridVo gridVo = new BaseGridVo();
		// paginationHandle(gridVo);
		List<ErpDeptVo> list = erpDeptHandler.queryDeptList();
		gridVo.setJsonList(list);
		gridVo.setRecords(list.size());
		return gridVo;
	}

	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public String addNew() throws Exception {
		return "deptment/deptEdit";
	}

	@RequestMapping("/detail")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public ModelAndView detail(String deptId) throws Exception {
		ModelAndView mv = new ModelAndView("deptment/deptEdit");
		mv.addObject("erpDept", erpDeptHandler.getDept(deptId));
		return mv;
	}

	@RequestMapping("/saveOrUpdate")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public String saveOrUpdate(ErpDeptVo deptVo) throws Exception {
		erpDeptHandler.saveOrUpdate(deptVo);
		return "redirect:/dept/list";
	}

	@RequestMapping("/delete")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public String delete(String deptId) throws Exception {
		erpDeptHandler.deleteDept(deptId);
		return "redirect:/dept/list";
	}

	@RequestMapping("/requestDepts")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public void ajaxDepts(HttpServletResponse response) throws Exception {
		responseJson(response, erpDeptHandler.queryDeptList());
	}

	@RequestMapping("/checkIsRepeat")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public void ajaxCheckDeptIsRepeat(String id, String code, HttpServletResponse response) throws Exception {
		responseJson(response, erpDeptHandler.checkIsRepeat(id, code));
	}

	@RequestMapping("/countUsers")
	@RequiresPermissions("ERP_AUTH_DEPT")
	public void countUsers(String deptId, HttpServletResponse response) throws Exception {
		responseJson(response, erpDeptHandler.countUsersByDept(deptId));
	}

}
