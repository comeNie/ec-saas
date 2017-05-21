package com.yjg.ec.platform.login.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

public class LoginUserParamDto {

	private String type;

	@NotBlank(message = "用户名不能为空")
	private String username;

	@NotBlank(message = "密码不能为空")
	private String password;

	/**
	 * 图片验证码
	 */
	private String captchaCode;

	/**
	 * app系统版本
	 */
	private String appSysVersion;

	/**
	 * 设备唯一码
	 */
	private String deviceId;

	/**
	 * 设备类型(1，android；2，ios；3，winPhone)
	 */
	private String deviceType;

	/**
	 * 手机品牌型号
	 */
	private String deviceBrandModel;

	/**
	 * 手机系统版本
	 */
	private String deviceSysVersion;

	public String getAppSysVersion() {
		return appSysVersion;
	}

	public void setAppSysVersion(String appSysVersion) {
		this.appSysVersion = appSysVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceBrandModel() {
		return deviceBrandModel;
	}

	public void setDeviceBrandModel(String deviceBrandModel) {
		this.deviceBrandModel = deviceBrandModel;
	}

	public String getDeviceSysVersion() {
		return deviceSysVersion;
	}

	public void setDeviceSysVersion(String deviceSysVersion) {
		this.deviceSysVersion = deviceSysVersion;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
