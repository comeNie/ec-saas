package com.yjg.ec.platform.security.service;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.StretchGimpyRenderer;
import cn.apiclub.captcha.text.producer.NumbersAnswerProducer;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjg.ec.platform.common.Result;

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
public interface ScurityService {

	/**
	 * 获取图片验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public Result<byte[]> getCaptcha(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 校验图片验证码是否正确
	 * 
	 * @param captchaCode
	 * @param captchaCodeRedisKey
	 * @return
	 */
	public Result<Boolean> validateCaptchaCode(String captchaCode, String captchaCodeRedisKey);

	/**
	 * 获取手机验证码
	 * 
	 * @param paramDto
	 * @param request
	 * @return
	 */
	public Result<String> getPhoneCode(SecurityGetPhoneCodeParamDto paramDto, HttpServletRequest request);

	/**
	 * 校验手机验证码
	 * 
	 * @param loginName
	 * @param phoneValidateCode
	 * @param type
	 * @return
	 */
	public Result<Boolean> validatePhoneCode(String loginName, String phoneValidateCode, String type);

	/**
	 * 授信
	 *
	 * @param paramDto
	 * @param request
	 * @return
	 */
	public Result<String> creditDevice(SecurityCreditDeviceParamDto paramDto, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 检验是否第一次登录
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	public Result<Boolean> checkFirstLogin(LoginDeviceInfoParamDto deviceInfoDto);

	/**
	 * 检验deviceId是否已存在（此设备是否受信）
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	public Result<Boolean> checkDeviceIdExist(LoginDeviceInfoParamDto deviceInfoDto);

	/**
	 * 保存设备信息
	 * 
	 * @param deviceInfoDto
	 * @return
	 */
	public Result<Boolean> saveDeviceInfo(LoginDeviceInfoParamDto deviceInfoDto);
}
