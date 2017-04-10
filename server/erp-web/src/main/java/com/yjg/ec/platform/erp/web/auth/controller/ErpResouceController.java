package com.yjg.ec.platform.erp.web.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.auth.api.ErpAuthorityApi;
import com.yjg.ec.platform.erp.auth.api.ErpResouceApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.param.dto.ErpResouceParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpResouceResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.UserContext;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpAuthorityParamVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpResourceParamVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * http://localhost:6060/erpauthority/list Created by gus on 2015/8/28.
 */
@Controller
@RequestMapping("/erpresouce")
public class ErpResouceController extends BaseController {

	@Resource
	private ErpResouceApi erpResouceApi;

	@Resource
	private ErpAuthorityApi erpAuthorityApi;

	@Resource
	private Mapper dozerMapper;

	/**
	 * 初始化菜单列表页
	 *
	 * @param erpResourceParamVo
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public ModelAndView initResourceList(ErpResourceParamVo erpResourceParamVo) throws Exception {
		ModelAndView mav = new ModelAndView("resouce/resourceList");
		return mav;
	}

	/**
	 * 获取菜单管理列表
	 *
	 * @param erpResourceParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getResourceList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public BaseGridVo getResourceList(ErpResourceParamVo erpResourceParamVo) throws Exception {
		ErpResouceParamDto erpResouce = dozerMapper.map(erpResourceParamVo, ErpResouceParamDto.class);
		BaseGridVo baseGridVo = erpResourceParamVo;
		List<ErpResouceResultDto> list = erpResouceApi.queryErpResouceListByCondition(erpResouce);
		baseGridVo.setRecords(list.size());
		paginationHandle(baseGridVo);
		baseGridVo.setJsonList(list);
		return baseGridVo;
	}

	@RequestMapping("/info")
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public ModelAndView authorityInfo(ErpResouceParamDto erpResouce) throws Exception {
		ModelAndView mav = new ModelAndView("resouce/resourceAdd");
		List<ErpResouceResultDto> list = erpResouceApi.queryErpResouce(erpResouce.getId());
		mav.addObject("erpResouce", list.get(0));
		return mav;
	}

	/**
	 * 初始化父菜单集合弹出窗
	 * 
	 * @param erpResourceParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/initFatherResourceList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public List<ErpResouceResultDto> initFatherResourceList(ErpResourceParamVo erpResourceParamVo) throws Exception {
		ErpResouceParamDto erpResouce = dozerMapper.map(erpResourceParamVo, ErpResouceParamDto.class);
		List<ErpResouceResultDto> list = erpResouceApi.queryErpResouceListByCondition(erpResouce);
		return list;
	}

	/**
	 * 初始化权限集合弹出窗
	 * 
	 * @param erpAuthorityParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/initAuthList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public List<ErpAuthorityResultDto> initAuthList(ErpAuthorityParamVo erpAuthorityParamVo) throws Exception {
		ErpAuthorityParamDto erpAuthority = dozerMapper.map(erpAuthorityParamVo, ErpAuthorityParamDto.class);
		List<ErpAuthorityResultDto> list = erpAuthorityApi.queryErpAuthorityList(erpAuthority);
		return list;
	}

	/**
	 * 初始化权限管理新增页
	 *
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public String authorityAdd() throws Exception {
		return "resouce/resourceAdd";
	}

	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public boolean authorityDelete(ErpResouceParamDto erpResouce) throws Exception {
		Integer row = erpResouceApi.deleteErpResouce(erpResouce.getId());
		return row > 0;
	}

	/**
	 * 新增或更新权限
	 *
	 * @param erpResouce
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_RESOUCE")
	public boolean resouceUpdate(ErpResouceParamDto erpResouce) throws Exception {
		Integer result;
		if (erpResouce.getId() != null) {
			erpResouce.setModify_id(UserContext.getCurrentUser().getId());
			result = erpResouceApi.updateErpResouce(erpResouce);
		} else {
			erpResouce.setCreate_id(UserContext.getCurrentUser().getId());
			erpResouce.setCreate_time(new Date());
			erpResouce.setModify_id(UserContext.getCurrentUser().getId());
			result = erpResouceApi.saveErpResouce(erpResouce);
		}
		return result > 0;
	}

}
