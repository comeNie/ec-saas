package com.yjg.ec.platform.security.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 医生登录设备信息domain
 * Created by mengzipeng on 2017/3/24.
 */
public class LoginDeviceInfoDomain {

    private Integer id;

    /**
     * 医生登录名
     */
    private String doctorLoginName;

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

    /**
     * 创建时间
     */
    private Date cT;

    /**
     * 更新时间
     */
    private Date uT;

    /**
     * 删除标识(0,未删除；1，已删除)
     */
    private Byte isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorLoginName() {
        return doctorLoginName;
    }

    public void setDoctorLoginName(String doctorLoginName) {
        this.doctorLoginName = doctorLoginName;
    }

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

    public Date getcT() {
        return cT;
    }

    public void setcT(Date cT) {
        this.cT = cT;
    }

    public Date getuT() {
        return uT;
    }

    public void setuT(Date uT) {
        this.uT = uT;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
