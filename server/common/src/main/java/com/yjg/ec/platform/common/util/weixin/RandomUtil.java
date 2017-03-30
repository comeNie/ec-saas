package com.yjg.ec.platform.common.util.weixin;

import java.util.Random;

public class RandomUtil {

    /**
     * 获取�?定长度的随机字符�?
     * @param length 指定字符串长�?
     * @return �?定长度的字符�?
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
