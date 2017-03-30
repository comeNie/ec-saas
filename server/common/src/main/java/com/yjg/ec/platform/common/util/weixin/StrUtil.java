package com.yjg.ec.platform.common.util.weixin;

public class StrUtil {
	 /**
     * 此类不需要实例化
     */
    private StrUtil() {
    }

    /**
     * 判断�?个字符串是否为空，null也会返回true
     *
     * @param str �?要判断的字符�?
     * @return 是否为空，null也会返回true
     */
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * 判断�?个字符串是否不为�?
     *
     * @param str �?要判断的字符�?
     * @return 是否为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断�?组字符串是否有空�?
     *
     * @param strs �?要判断的�?组字符串
     * @return 判断结果，只要其中一个字符串为null或�?�为空，就返回true
     */
    public static boolean hasBlank(String... strs) {
        if (null == strs || 0 == strs.length) {
            return true;
        } else {
            //这种代码如果用java8就会很优雅了
            for (String str : strs) {
                if (isBlank(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
