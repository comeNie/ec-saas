package com.yjg.ec.platform.constants;

/**
 * 审批状态
 * @author Mengzipeng
 * @date 2015/9/14
 */
public enum StatusType {

    YES(1, "有效"),
    NO(0, "无效");

    private Integer type;
    private String description;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    StatusType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public static String getDescriptionByKey(Integer type) {
        if (type == null) {
            return "";
        }
        StatusType[] values = StatusType.values();
        String retValue = null;
        for (StatusType value : values) {
            if (value.type.equals(type)) {
                retValue = value.description;
                break;
            }
        }
        return retValue;
    }
}
