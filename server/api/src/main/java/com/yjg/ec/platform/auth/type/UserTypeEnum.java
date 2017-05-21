package com.yjg.ec.platform.auth.type;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by meng on 2016/12/7.
 */
public enum UserTypeEnum {
    PATIENT("PATIENT", 1),
    DOCTOR("DOCTOR", 2);
    
    
    private String desc;
    private Integer type;
    
    UserTypeEnum(String desc, Integer type) {
        this.desc = desc;
        this.type = type;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public static Integer getTypeBydesc(String desc) {
        if (StringUtils.isBlank(desc)) {
            return 0;
        }
        UserTypeEnum[] values = UserTypeEnum.values();
        Integer type = null;
        for (UserTypeEnum value : values) {
            if (value.desc.equals(desc)) {
                type = value.type;
                break;
            }
        }
        return type;
    }
}
