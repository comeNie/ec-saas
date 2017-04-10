package com.yjg.ec.platform.erp.web.auth.controller;

import net.sf.json.JSONArray;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.auth.api.ErpAuthorityApi;
import com.yjg.ec.platform.erp.auth.api.ErpResouceApi;
import com.yjg.ec.platform.erp.auth.api.ErpRoleApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpRoleParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityRelationResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleAuthorityResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpRoleResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.UserContext;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpAuthorityRelationVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpRoleParamVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * http://localhost:6060/erpauthority/list Created by gus on 2015/8/28.
 */
@Controller
@RequestMapping("/erprole")
public class ErpRoleController extends BaseController {

	@Resource
	public ErpAuthorityApi erpAuthorityApi;

	@Resource
	public ErpRoleApi erpRoleApi;

	@Resource
	public ErpResouceApi erpResouceApi;

	@Resource
	private Mapper dozerMapper;

	/**
	 * 初始化角色列表页
	 * 
	 * @param erpRoleParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_ROLE")
	public ModelAndView initRoleList(ErpRoleParamVo erpRoleParamVo) throws Exception {
		ModelAndView mav = new ModelAndView("role/roleList");
		return mav;
	}

	/**
	 * 获取角色管理列表
	 *
	 * @param erpRoleParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_ROLE")
	public BaseGridVo getRoleList(ErpRoleParamVo erpRoleParamVo) throws Exception {
		ErpRoleParamDto erpRole = dozerMapper.map(erpRoleParamVo, ErpRoleParamDto.class);
		BaseGridVo baseGridVo = erpRoleParamVo;
		List<ErpRoleResultDto> list = erpRoleApi.queryErpRoleList(erpRole);
		baseGridVo.setRecords(list.size());
		paginationHandle(baseGridVo);
		baseGridVo.setJsonList(list);
		return baseGridVo;
	}

	@RequestMapping("/info")
	@RequiresPermissions("ERP_AUTH_ROLE")
	public ModelAndView roleInfo(ErpRoleParamDto erpRole) throws Exception {
		ModelAndView mav = new ModelAndView("role/roleAdd");
		List<ErpRoleResultDto> list = erpRoleApi.queryErpRole(erpRole.getId());
		mav.addObject("erpRole", list.get(0));
		return mav;
	}

	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_ROLE")
	public String roleAdd() {
		return "role/roleAdd";
	}

	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_ROLE")
	public boolean roleDelete(ErpRoleParamDto erpRole) throws Exception {
		int rows = erpRoleApi.deleteErpRole(erpRole.getId());
		// 查询角色列表
		return rows > 0;
	}

	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_ROLE")
	public boolean roleUpdate(ErpRoleParamDto erpRole) throws Exception {
		Integer result;
		if (erpRole.getId() != null) {
			erpRole.setModify_id(UserContext.getCurrentUser().getId());
			result = erpRoleApi.updateErpRole(erpRole);
		} else {
			erpRole.setCreate_id(UserContext.getCurrentUser().getId());
			erpRole.setCreate_time(new Date());
			erpRole.setModify_id(UserContext.getCurrentUser().getId());
			result = erpRoleApi.saveErpRole(erpRole);
		}
		return result > 0;
	}

	@RequestMapping("/roleAuthList")
	@RequiresPermissions("ERP_AUTH_ROLE")
	public ModelAndView roleAuthList(ErpRoleParamDto erpRole) throws Exception {
		ModelAndView mav = new ModelAndView("role/roleAuthList");
		// 查询已关联权限列表
		final List<ErpRoleAuthorityResultDto> list = erpRoleApi.queryErpRoleAuthorityList(erpRole.getId());
		// 查询所有权限列表
		List<ErpAuthorityRelationResultDto> relationDtoList = erpAuthorityApi.queryErpAuthrityRelationList();

		final List<ErpAuthorityRelationVo> relationVoList = new ArrayList<ErpAuthorityRelationVo>();
		CollectionUtils.forAllDo(relationDtoList, new Closure() {
			@Override
			public void execute(Object input) {
				ErpAuthorityRelationResultDto erpAuthorityRelationDto = (ErpAuthorityRelationResultDto) input;
				ErpAuthorityRelationVo erpAuthorityRelationVo = dozerMapper.map(erpAuthorityRelationDto,
						ErpAuthorityRelationVo.class);
				for (ErpRoleAuthorityResultDto roleAuthority : list) {
					if (erpAuthorityRelationVo.getId() == roleAuthority.getAuthority_id()) {
						erpAuthorityRelationVo.setChecked(true);
					}
				}
				relationVoList.add(erpAuthorityRelationVo);
			}
		});
		JSONArray jsonArray = JSONArray.fromObject(relationVoList);
		mav.addObject("relationVoList", jsonArray.toString());
		mav.addObject("erpRole", erpRole);
		return mav;
	}

	@RequestMapping("/updateRoleAuth")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_ROLE")
	public boolean updateRoleAuth(HttpServletRequest request) throws Exception {
		String authStr = request.getParameter("authStr");
		String roleId = request.getParameter("roleId");
		String[] authArr = StringUtils.split(authStr, ",");
		if (StringUtils.isNotEmpty(roleId)) {
			erpRoleApi.deleteErpRoleAuthority(Integer.parseInt(roleId));
		}
		int row;
		for (String authId : authArr) {
			ErpRoleAuthorityParamDto erpRoleAuthority = new ErpRoleAuthorityParamDto();
			if (StringUtils.isNotEmpty(authId) && StringUtils.isNotEmpty(roleId)) {
				erpRoleAuthority.setRole_id(Integer.parseInt(roleId));
				erpRoleAuthority.setAuthority_id(Integer.parseInt(authId));
				row = erpRoleApi.saveErpRoleAuthority(erpRoleAuthority);
				if (row <= 0) {
					return false;
				}
			}
		}
		return true;
	}

}
