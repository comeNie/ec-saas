package com.yjg.ec.platform.security.service.impl;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.StretchGimpyRenderer;
import cn.apiclub.captcha.text.producer.NumbersAnswerProducer;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.common.util.CookieUtil;
import com.yjg.ec.platform.common.util.RedisUtil;
import com.yjg.ec.platform.security.dao.SecurityDao;
import com.yjg.ec.platform.security.service.ScurityService;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mengzipeng on 2017/3/24.
 */
@Service
public class ScurityServiceImpl implements ScurityService {

	Logger logger = LoggerFactory.getLogger(ScurityServiceImpl.class);

	@Resource
	private DoctorApi doctorApi;

	@Resource
	private SmsApi smsApi;

	@Resource
	private AuthApi authApi;

	@Autowired
	private SecurityDao doctorSecurityDao;

	@Resource
	private RedisUtil redisUtil;

	@Resource
	private CookieUtil cookieUtil;

	@Autowired
	private Mapper mapper;

	private static Long captchaExpires = 3 * 60L; // 超时时间3min
	private static int captchaW = 100;
	private static int captchaH = 80;

	/**
	 * 获取图片验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public Result<byte[]> getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		// 生成验证码
		Captcha captcha = new Captcha.Builder(captchaW, captchaH).addText(new NumbersAnswerProducer(4))
				.addBackground(new GradiatedBackgroundProducer(Color.GRAY, Color.LIGHT_GRAY))
				.gimp(new StretchGimpyRenderer(3D, 3D)).build();

		// 将验证码以<key,value>形式缓存到redis
		String uuid = UUID.randomUUID().toString();
		redisUtil.set(uuid, captcha.getAnswer(), captchaExpires);

		// 将验证码key，及验证码的图片返回
		cookieUtil.addCookie(request, response, SecurityConstants.IMG_CAPTCHA_COOKIE_NAME, uuid, 0);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		try {
			ImageIO.write(captcha.getImage(), "jpg", bao);
			return Result.buildSuccessResult(bao.toByteArray());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return Result.buildBusinessErrorResult("");
		}
	}

	/**
	 * 校验图片验证码是否正确
	 * 
	 * @param captchaCode
	 * @param captchaCodeRedisKey
	 * @return
	 */
	public Result<Boolean> validateCaptchaCode(String captchaCode, String captchaCodeRedisKey) {
		String redisValue = redisUtil.get(captchaCodeRedisKey) + "";
		if (StringUtils.isBlank(redisValue))
			throw new BusinessException("图片验证码已过期，请重新获取");
		if (StringUtils.isBlank(captchaCodeRedisKey)
				|| !captchaCode.equalsIgnoreCase(redisUtil.get(captchaCodeRedisKey) + ""))
			throw new BusinessException("图片验证码输入有误，请重新输入");
		return Result.buildSuccessResult(true);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param paramDto
	 * @param request
	 * @return
	 */
	public Result<String> getPhoneCode(SecurityGetPhoneCodeParamDto paramDto, HttpServletRequest request) {
		// 先验证图片验证码是否正确
		Cookie cookie = cookieUtil.getCookieByName(request, SecurityConstants.IMG_CAPTCHA_COOKIE_NAME);
		if (cookie == null || StringUtils.isBlank(cookie.getValue()))
			throw new BusinessException("图片验证码已过期，请重新获取");
		if (!validateCaptchaCode(paramDto.getCaptchaCode(), cookie.getValue()).getResultData())
			throw new BusinessException("图片验证码输入有误，请稍后重试！");

		// 根据登录名获取基本信息
		Result<Doctor> doctorResult = doctorApi.getDoctorInfoByLoginName(paramDto.getLoginName());
		if (doctorResult == null || doctorResult.getCode() != 200 || doctorResult.getResultData() == null) {
			throw new BusinessException(doctorResult == null ? "根据登录名获取基本信息返回结果为空" : doctorResult.getMessage());
		}
		if (StringUtils.isBlank(doctorResult.getResultData().getPhoneNumber()))
			throw new BusinessException("医生手机号不能为空");
		Result<Boolean> sendValidateCodeResult = smsApi
				.sendValidateCodeInner(doctorResult.getResultData().getPhoneNumber(), paramDto.getType());
		if (sendValidateCodeResult == null || sendValidateCodeResult.getCode() != 200
				|| sendValidateCodeResult.getResultData() == null) {
			throw new BusinessException(
					sendValidateCodeResult == null ? "发送手机验证码返回结果为空" : sendValidateCodeResult.getMessage());
		}
		String resultMsg = "手机号 "
				+ doctorResult.getResultData().getPhoneNumber().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")
				+ " 验证码已发送，请查收";
		return Result.buildSuccessResult("", resultMsg);
	}

	/**
	 * 校验手机验证码
	 * 
	 * @param loginName
	 * @param phoneValidateCode
	 * @param type
	 * @return
	 */
	public Result<Boolean> validatePhoneCode(String loginName, String phoneValidateCode, String type) {
		// 根据登录名获取基本信息
		Result<Doctor> doctorResult = doctorApi.getDoctorInfoByLoginName(loginName);
		if (doctorResult == null || doctorResult.getCode() != 200 || doctorResult.getResultData() == null) {
			throw new BusinessException(doctorResult == null ? "根据登录名获取基本信息返回结果为空" : doctorResult.getMessage());
		}

		// 校验手机验证码是否正确
		if (StringUtils.isBlank(doctorResult.getResultData().getPhoneNumber()))
			throw new BusinessException("医生手机号不能为空");
		Result<Boolean> validateResult = smsApi.validateCode(doctorResult.getResultData().getPhoneNumber(), type,
				phoneValidateCode);
		if (validateResult == null || validateResult.getCode() != 200 || validateResult.getResultData() == null) {
			throw new BusinessException(validateResult == null ? "校验手机验证码返回结果为空" : validateResult.getMessage());
		}
		if (!validateResult.getResultData())
			throw new BusinessException("手机验证码输入有误，请重新输入");
		return Result.buildSuccessResult(true);
	}

	/**
	 * 授信
	 *
	 * @param paramDto
	 * @param request
	 * @return
	 */
	@Transactional
	public Result<String> creditDevice(SecurityCreditDeviceParamDto paramDto, HttpServletRequest request,
			HttpServletResponse response) {
		// 根据登录名获取基本信息
		Result<Doctor> doctorResult = doctorApi.getDoctorInfoByLoginName(paramDto.getLoginName());
		if (doctorResult == null || doctorResult.getCode() != 200 || doctorResult.getResultData() == null) {
			throw new BusinessException(doctorResult == null ? "根据登录名获取基本信息返回结果为空" : doctorResult.getMessage());
		}

		// 校验手机验证码是否正确
		if (StringUtils.isBlank(doctorResult.getResultData().getPhoneNumber()))
			throw new BusinessException("医生手机号不能为空");
		Result<Boolean> validateResult = smsApi.validateCode(doctorResult.getResultData().getPhoneNumber(),
				SmsConstants.VALIDATECODE_TYPE_LOGIN_SECURITY, paramDto.getPhoneValidateCode());
		if (validateResult == null || validateResult.getCode() != 200 || validateResult.getResultData() == null) {
			throw new BusinessException(validateResult == null ? "校验手机验证码返回结果为空" : validateResult.getMessage());
		}
		if (!validateResult.getResultData())
			throw new BusinessException("手机验证码输入有误，请重新输入");

		// 免密登录
		LoginUserPasswordFreeDto loginUserPasswordFreeDto = new LoginUserPasswordFreeDto();
		loginUserPasswordFreeDto.setUsername(paramDto.getLoginName());
		loginUserPasswordFreeDto.setSessionId(request.getSession().getId());
		loginUserPasswordFreeDto.setType("DOCTOR");
		Result<String> loginResult = authApi.loginPasswordFree(loginUserPasswordFreeDto, null);
		if (loginResult == null || loginResult.getCode() != 200 || loginResult.getResultData() == null) {
			throw new BusinessException(loginResult == null ? "免密登录返回结果为空" : loginResult.getMessage());
		}

		// 保存设备信息
		DoctorLoginDeviceInfoDto deviceInfoDto = mapper.map(paramDto, DoctorLoginDeviceInfoDto.class);
		Result<Boolean> saveResult = saveDeviceInfo(deviceInfoDto);
		if (saveResult == null || saveResult.getCode() != 200 || saveResult.getResultData() == null) {
			throw new BusinessException(saveResult == null ? "保存设备信息返回结果为空" : saveResult.getMessage());
		}
		cookieUtil.addCookie(request, response, "sid", loginResult.getResultData(), 0);
		return loginResult;
	}

	/**
	 * 检验是否第一次登录
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	public Result<Boolean> checkFirstLogin(LoginDeviceInfoParamDto deviceInfoDto) {
		SecurityCheckParamDto checkParamDto = mapper.map(deviceInfoDto, SecurityCheckParamDto.class);
		checkParamDto.setDeviceId(null);
		if (doctorSecurityDao.countDeviceByLoginName(checkParamDto) > 0)
			return Result.buildSuccessResult(false);
		return Result.buildSuccessResult(true);
	}

	/**
	 * 检验deviceId是否已存在（此设备是否受信）
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	public Result<Boolean> checkDeviceIdExist(LoginDeviceInfoParamDto deviceInfoDto) {
		SecurityCheckParamDto checkParamDto = mapper.map(deviceInfoDto, SecurityCheckParamDto.class);
		int i = doctorSecurityDao.countDeviceByLoginName(checkParamDto);
		if (i > 0) {
			return Result.buildSuccessResult(true);
		} else {
			if (checkFirstLogin(deviceInfoDto).getResultData())
				return Result.buildSuccessResult(true);
		}
		return Result.buildSuccessResult(false);
	}

	/**
	 * 保存设备信息
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	@Transactional
	public Result<Boolean> saveDeviceInfo(LoginDeviceInfoParamDto deviceInfoDto) {
		LoginDeviceInfoDomain deviceInfoDomain = mapper.map(deviceInfoDto, LoginDeviceInfoDomain.class);
		deviceInfoDomain.setIsDeleted((byte) 0);
		if (doctorSecurityDao.insert(deviceInfoDomain) > 0) {
			return Result.buildSuccessResult(true);
		}
		return Result.buildSuccessResult(false);
	}
}
