package com.yjg.ec.platform.common.constant;

/**
 * Created by mengzipeng on 2017/3/24.
 */
public final class SecurityConstants {

    /**
     * 医生登录错误次数key前缀
     */
    public static final String DOCTOR_LOGIN_ERROR_SECURITY_KEY_PREFIX = "doctor_login_error_security_";

    /**
     * 医生登录错误次数限制
     */
    public static final Integer DOCTOR_LOGIN_ERROR_SECURITY_TIME_LIMIT = 3;

    public static final String IMG_CAPTCHA_COOKIE_NAME = "CaptchaCode";
}
