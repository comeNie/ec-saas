package com.yjg.ec.platform.security.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.exception.ParamException;
import com.yjg.ec.platform.security.service.ScurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by mengzipeng on 2017/3/24.
 */
@RestController
public class SecurityApiImpl implements SecurityApi {

	@Autowired
	private ScurityService scurityService;

	/**
	 * 校验图片验证码是否正确
	 * 
	 * @param captchaCode
	 * @param captchaCodeRedisKey
	 * @return
	 */
	@Override
	public Result<Boolean> validateCaptchaCode(@PathVariable String captchaCode,
			@PathVariable String captchaCodeRedisKey) {
		if (StringUtils.isBlank(captchaCode) || StringUtils.isBlank(captchaCodeRedisKey))
			throw new ParamException("图片验证码不能为空");
		return scurityService.validateCaptchaCode(captchaCode, captchaCodeRedisKey);
	}

	/**
	 * 获取图片验证码
	 *
	 * @param response
	 * @return
	 */
	@Override
	@NotAuthenRequired
	public Result<byte[]> getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		return doctorScurityService.getCaptcha(request, response);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param paramDto
	 * @param request
	 * @return
	 */
	@Override
	@NotAuthenRequired
	public Result<String> getPhoneCode(@Valid DoctorSecurityGetPhoneCodeParamDto paramDto, Errors errors,
			HttpServletRequest request) {
		if (errors.hasErrors())
			throw new ParamException(errors);
		return doctorScurityService.getPhoneCode(paramDto, request);
	}

	/**
	 * 校验手机验证码
	 *
	 * @param loginName
	 * @param phoneValidateCode
	 * @param type
	 * @return
	 */
	@Override
	@NotAuthenRequired
	public Result<Boolean> validatePhoneCode(String loginName, String phoneValidateCode, String type) {
		if (StringUtils.isBlank(loginName)) {
			throw new ParamException("登录名不能为空！");
		}
		if (StringUtils.isBlank(phoneValidateCode)) {
			throw new ParamException("手机验证码不能为空");
		}
		if (StringUtils.isBlank(type)) {
			throw new ParamException("手机验证码类型不能为空");
		}
		return doctorScurityService.validatePhoneCode(loginName, phoneValidateCode, type);
	}

	/**
	 * 授信
	 *
	 * @param paramDto
	 * @param request
	 * @return
	 */
	@Override
	@NotAuthenRequired
	public Result<String> creditDevice(@Valid DoctorSecurityCreditDeviceParamDto paramDto, Errors errors,
			HttpServletRequest request, HttpServletResponse response) {
		if (errors.hasErrors())
			throw new ParamException(errors);
		return doctorScurityService.creditDevice(paramDto, request, response);
	}

	/**
	 * 检验是否第一次登录
	 *
	 * @param deviceInfoDto
	 * @return
	 */
	@Override
	public Result<Boolean> checkFirstLoginForDoctor(@RequestBody @Valid DoctorLoginDeviceInfoDto deviceInfoDto,
			Errors errors) {
		if (errors.hasErrors())
			throw new ParamException(errors);
		return doctorScurityService.checkFirstLogin(deviceInfoDto);
	}

	/**
	 * 检验deviceId是否已存在（此设备是否受信）
	 *
	 * @param deviceInfoDto
	 * @return
	 */
	@Override
	public Result<Boolean> checkDeviceIdExistForDoctor(@RequestBody @Valid DoctorLoginDeviceInfoDto deviceInfoDto,
			Errors errors) {
		if (errors.hasErrors())
			throw new ParamException(errors);
		return doctorScurityService.checkDeviceIdExist(deviceInfoDto);
	}

	/**
	 * 保存设备信息
	 *
	 * @param deviceInfoDto
	 * @return
	 */
	@Override
	public Result<Boolean> saveDeviceInfoForDoctor(@RequestBody @Valid DoctorLoginDeviceInfoDto deviceInfoDto,
			Errors errors) {
		if (errors.hasErrors())
			throw new ParamException(errors);
		return doctorScurityService.saveDeviceInfo(deviceInfoDto);
	}
}
