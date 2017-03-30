package com.yjg.ec.platform.common.util.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SignUtil {
	private static final Logger LOG   = LoggerFactory.getLogger(SignUtil.class);
    private static final char[] digit = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 此类不需要实例化
     */
    private SignUtil() {
    }

    /**
     * 认证微信，可以参见微信开发�?�文�?
     *
     * @param token     我们自己设定的token�?
     * @param signature 微信传来的变�?
     * @param timestamp 微信传来的变�?
     * @param nonce     微信传来的变�?
     * @return 是否合法
     */
    public static boolean checkSignature(String token, String signature,
                                         String timestamp, String nonce) {
        if (StrUtil.hasBlank(token, signature, timestamp, nonce)) {
            return false;
        }
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("加密方式异常", e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("编码格式不支�?", e);
        }
        return tmpStr != null && tmpStr.equalsIgnoreCase(signature);
    }

    /**
     * 将byte数组变为16进制对应的字符串
     *
     * @param byteArray byte数组
     * @return 转换结果
     */
    private static String byteToStr(byte[] byteArray) {
        int len = byteArray.length;
        StringBuilder strDigest = new StringBuilder(len * 2);
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);
    }
    
    public static String getSHA1(String str) {
		MessageDigest md;
		String ret = "";
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(str.getBytes("UTF-8"));
            ret = byteToStr(digest);
        }
        catch(Exception e) {
        	return "";
        }
        return ret;
	}
}
