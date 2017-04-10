package com.yjg.ec.platform.erp.web.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yjg.ec.platform.erp.auth.api.ErpAuthorityApi;
import com.yjg.ec.platform.erp.auth.param.dto.ErpAuthorityParamDto;
import com.yjg.ec.platform.erp.auth.result.dto.ErpAuthorityResultDto;
import com.yjg.ec.platform.erp.integration.auth.common.UserContext;
import com.yjg.ec.platform.erp.web.auth.vo.BaseGridVo;
import com.yjg.ec.platform.erp.web.auth.vo.ErpAuthorityParamVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * http://localhost:6060/erpauthority/list Created by gus on 2015/8/28.
 */
@RestController
@RequestMapping("/erpauthority")
public class ErpAuthorityController extends BaseController {

	@Resource
	private ErpAuthorityApi erpAuthorityApi;

	@Resource
	private Mapper dozerMapper;

	/**
	 * 初始化权限列表页
	 *
	 * @param erpAuthorityParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public ModelAndView initAuthorityList(ErpAuthorityParamVo erpAuthorityParamVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView("authority/authorityList");
		return modelAndView;
	}

	/**
	 * 获取权限管理列表
	 *
	 * @param erpAuthorityParamVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAuthorityList")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public BaseGridVo getAuthorityList(ErpAuthorityParamVo erpAuthorityParamVo) throws Exception {
		ErpAuthorityParamDto erpAuthority = dozerMapper.map(erpAuthorityParamVo, ErpAuthorityParamDto.class);
		BaseGridVo baseGridVo = erpAuthorityParamVo;
		List<ErpAuthorityResultDto> list = erpAuthorityApi.queryErpAuthorityList(erpAuthority);
		baseGridVo.setRecords(list.size());
		paginationHandle(baseGridVo);
		baseGridVo.setJsonList(list);
		return baseGridVo;
	}

	@RequestMapping("/info")
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public ModelAndView authorityInfo(ErpAuthorityParamDto erpAuthorityParamDto) throws Exception {
		ModelAndView mav = new ModelAndView("authority/authorityAdd");
		List<ErpAuthorityResultDto> list = erpAuthorityApi.queryErpAuthority(erpAuthorityParamDto.getId());
		mav.addObject("erpAuthority", list.get(0));
		return mav;
	}

	/**
	 * 初始化权限管理新增页
	 *
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public String authorityAdd() throws Exception {
		return "authority/authorityAdd";
	}

	/**
	 * 删除权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public boolean authorityDelete(ErpAuthorityParamDto erpAuthorityParamDto) throws Exception {
		Integer row = erpAuthorityApi.deleteAuthority(erpAuthorityParamDto.getId());
		return row > 0;
	}

	/**
	 * 新增或更新权限
	 *
	 * @param erpAuthority
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("ERP_AUTH_AUTHORITY")
	public boolean authorityUpdate(ErpAuthorityParamDto erpAuthorityParamDto) throws Exception {
		Integer result;
		if (erpAuthorityParamDto.getId() != null) {
			erpAuthorityParamDto.setModify_id(UserContext.getCurrentUser().getId());
			result = erpAuthorityApi.updateErpAuthority(erpAuthorityParamDto);
		} else {
			erpAuthorityParamDto.setCreate_id(UserContext.getCurrentUser().getId());
			erpAuthorityParamDto.setCreate_time(new Date());
			erpAuthorityParamDto.setModify_id(UserContext.getCurrentUser().getId());
			result = erpAuthorityApi.saveErpAuthority(erpAuthorityParamDto);
		}
		return result > 0;
	}

}
