package com.yjg.ec.platform.erp.web.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.auth.api.ErpRoleApi;
import com.yjg.ec.platform.erp.auth.api.ErpUserApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpUserRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpUserRoleResultDto;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpUserParamVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gus on 2015/8/28.
 */
@Controller
@RequestMapping("/erpuser")
public class ErpUserController extends BaseController {

	@Resource
	public ErpRoleApi erpRoleApi;

	@Resource
	private ErpUserApi erpUserApi;

	@Resource
	private Mapper dozerMapper;

	/**
	 * 初始化用户列表页
	 * 
	 * @param erpUserParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_USER")
	public ModelAndView initUserList(ErpUserParamVo erpUserParamVo) throws Exception {
		ModelAndView mav = new ModelAndView("user/userList");
		return mav;
	}

	/**
	 * 获取用户管理列表
	 *
	 * @param erpUserParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_USER")
	public BaseGridVo getUserList(ErpUserParamVo erpUserParamVo) throws Exception {
		ErpUserParamDto erpUser = dozerMapper.map(erpUserParamVo, ErpUserParamDto.class);
		BaseGridVo baseGridVo = erpUserParamVo;
		List<ErpUserResultDto> list = erpUserApi.queryErpUserList(erpUser);
		baseGridVo.setRecords(list.size());
		paginationHandle(baseGridVo);
		baseGridVo.setJsonList(list);
		return baseGridVo;
	}

	@RequestMapping("/info")
	@RequiresPermissions("ERP_AUTH_USER")
	public ModelAndView userInfo(ErpUserParamDto erpUser) throws Exception {
		ModelAndView mav = new ModelAndView("user/userAdd");
		List<ErpUserResultDto> list = erpUserApi.queryErpUser(erpUser.getId());
		mav.addObject("erpUser", list.get(0));
		return mav;
	}

	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_USER")
	public String userAdd() throws Exception {
		return "user/userAdd";
	}

	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_USER")
	public boolean userUpdate(ErpUserParamDto erpUser) throws Exception {
		Integer result = 0;
		if (erpUser.getId() != null) {
			result = erpUserApi.updateErpUser(erpUser);
		} else {
			result = erpUserApi.saveErpUser(erpUser);
		}
		return result > 0;
	}

	@RequestMapping("/userRoleList")
	@RequiresPermissions("ERP_AUTH_USER")
	public ModelAndView userRoleList(ErpUserParamDto erpUser) throws Exception {
		ModelAndView mav = new ModelAndView("user/userRoleList");
		// 查询已关联角色列表
		List<ErpUserRoleResultDto> list = erpRoleApi.queryErpUserRoleList(erpUser.getId());
		// 查询所有角色列表
		List<ErpRoleResultDto> roleList = erpRoleApi.queryErpRoleList(new ErpRoleParamDto());
		for (ErpRoleResultDto role : roleList) {
			for (ErpUserRoleResultDto userRole : list) {
				if (role.getId() == userRole.getRole_id()) {
					role.setBindFlag(1);
					break;
				} else {
					role.setBindFlag(0);
				}
			}
		}
		mav.addObject("roleList", roleList);
		mav.addObject("erpUser", erpUser);
		return mav;
	}

	@RequestMapping("/updateUserRole")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_USER")
	public boolean updateUserRole(HttpServletRequest request) throws Exception {
		String roleStr = request.getParameter("roleStr");
		String userId = request.getParameter("userId");
		String[] roleArr = StringUtils.split(roleStr, ",");
		if (StringUtils.isNotEmpty(userId)) {
			erpRoleApi.deleteErpUserRole(Integer.parseInt(userId));
		}
		int row;
		for (String roleId : roleArr) {
			ErpUserRoleParamDto erpUserRole = new ErpUserRoleParamDto();
			if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(userId)) {
				erpUserRole.setUser_id(Integer.parseInt(userId));
				erpUserRole.setRole_id(Integer.parseInt(roleId));
				row = erpRoleApi.saveErpUserRole(erpUserRole);
				if (row <= 0) {
					return false;
				}
			}
		}
		return true;
	}

}
