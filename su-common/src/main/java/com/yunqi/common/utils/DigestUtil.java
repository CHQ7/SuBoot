package com.yunqi.common.utils;

import org.nutz.lang.Strings;

import java.security.MessageDigest;

/**
 * <p>
 *     摘要算法
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class DigestUtil {

    public static String sha256(CharSequence cs, CharSequence salt){
        return sha256(cs, salt, 1024);
    }

    public static String sha256(CharSequence cs, CharSequence salt, int iterations){
        return digest("SHA-256", Strings.getBytesUTF8(null == cs ? "" : cs) , Strings.getBytesUTF8(null == salt ? "" : salt), iterations );
    }

    /**
     * 十六进制字符串
     * @param hashBytes
     * @return
     */
    public static String fixedHexString(byte[] hashBytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashBytes.length; i++) {
            sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


    /**
     * 从字节数组计算出数字签名
     *
     * @param algorithm
     *            算法，比如 "SHA1" 或者 "MD5" 等
     * @param bytes
     *            字节数组
     * @param salt
     *            随机字节数组
     * @param iterations
     *            迭代次数
     * @return 数字签名
     */
    public static String digest(String algorithm, byte[] bytes, byte[] salt, int iterations) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                md.update(salt);
            }

            byte[] hashed = md.digest(bytes);

            for (int i = 1; i < iterations; i++) {
                md.reset();
                hashed = md.digest(hashed);
            }

            return fixedHexString(hashed);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
