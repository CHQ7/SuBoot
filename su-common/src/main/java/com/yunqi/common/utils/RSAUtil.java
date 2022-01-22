package com.yunqi.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.nutz.repo.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * <p>
 *      RSA 公钥/私钥/签名工具包
 * </p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * Created by @author JsckChin on 2022/1/22
 */
public class RSAUtil {

    /**
     * 指定加密算法为RSA
     */
    public static final String ALGORITHM_RSA = "RSA";

    /**
     * ECB 加密模式
     */
    private static final String ALGORITHM_RSA_ECB = "RSA/ECB/PKCS1Padding";

    /**
     * 签名模式
     */
    public static final String ALGORITHM_RSA_SIGN = "SHA256withRSA";

    /**
     * 密钥长度，用来初始化
     * 512 1024 2048 4096
     */
    private static final int KEY_SIZE  = 1024;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = (KEY_SIZE / 8) - 11;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = (KEY_SIZE / 8);


    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 生成密钥对(公钥和私钥)
     * @return Map
     */
    public static Map<String, String> genKeyPair(){
        try {
            // RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            // 初始化密钥对生成器
            keyPairGen.initialize(KEY_SIZE);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            Key privateKey = keyPair.getPrivate();
            Key publicKey = keyPair.getPublic();
            // 将公钥和私钥保存到Map
            Map<String, String> keyMap = new LinkedHashMap<>();
            keyMap.put(PUBLIC_KEY, Base64.encodeToString(publicKey.getEncoded(), false));
            keyMap.put(PRIVATE_KEY,  Base64.encodeToString(privateKey.getEncoded(), false));
            return keyMap;
        } catch (Exception e) {
            throw new RuntimeException("生成RAS密钥对异常");
        }
    }

    /**
     * 从字节串中恢复公钥
     * @param keyBytes Base64编码
     * @return 公钥
     */
    public static PublicKey publicKey(byte[] keyBytes) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception  e) {
            throw new RuntimeException("获取公钥异常");
        }
    }

    /**
     * 从字节串中恢复私钥
     * @param keyBytes Base64编码
     * @return 私钥
     */
    public static PrivateKey privateKey(byte[] keyBytes) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception  e) {
            throw new RuntimeException("获取私钥异常");
        }
    }


    /**
     * RSA公钥加密
     * @param publicKey     公钥(BASE64编码)
     * @param encryptData   明文字符串
     * @return              加密字符串
     */
    public static String encryptByPublicKey(String publicKey, String encryptData) {
        // 通过解码公钥(Base64编码)获取公钥
        RSAPublicKey pubKey = (RSAPublicKey) publicKey(Base64.decode(publicKey));
        // 模长
        int keyLen = pubKey.getModulus().bitLength()  / 8;
        // 加密数据长度 <= 模长-11,如果明文长度大于模长-11则要分组加密
        byte[] signData = decipher(Cipher.ENCRYPT_MODE, pubKey ,encryptData.getBytes(StandardCharsets.UTF_8) , keyLen -11);
        return Base64.encodeToString(signData, false);
    }

    /**
     * RSA私钥加密
     * @param privateKey    私钥(BASE64编码)
     * @param encryptData   明文字符串
     * @return              加密字符串
     */
    public static String encryptByPrivateKey(String privateKey, String encryptData) {
        // 通过解码私钥(Base64编码)获取私钥
        RSAPrivateKey priKey = (RSAPrivateKey) privateKey(Base64.decode(privateKey));
        int keyLen = (priKey.getModulus().bitLength()  / 8) - 11;
        // 加密数据长度 <= 模长-11,如果明文长度大于模长-11则要分组加密
        byte[] signData = decipher(Cipher.ENCRYPT_MODE, priKey, encryptData.getBytes(StandardCharsets.UTF_8), keyLen -11);
        return Base64.encodeToString(signData, false);
    }

    /**
     * RSA公钥解密
     * @param publicKey     公钥(BASE64编码)
     * @param decryptData   加密字符串
     * @return              明文字符串
     */
    public static String decryptByPublicKey(String publicKey, String decryptData) {
        // 通过解码公钥(Base64编码)获取公钥
        RSAPublicKey pubKey = (RSAPublicKey) publicKey(Base64.decode(publicKey));
        int keyLen = pubKey.getModulus().bitLength() / 8;
        //如果密文长度大于模长则要分组解密
        return new String(decipher(Cipher.DECRYPT_MODE, pubKey, Base64.decode(decryptData), keyLen));
    }

    /**
     * RSA私钥解密
     * @param privateKey    私钥(BASE64编码)
     * @param decryptData   加密字符串
     * @return              明文字符串
     */
    public static String decryptByPrivateKey(String privateKey, String decryptData) {
        // 通过解码私钥(Base64编码)获取私钥
        RSAPrivateKey priKey = (RSAPrivateKey) privateKey(Base64.decode(privateKey));
        int keyLen = priKey.getModulus().bitLength() / 8;
        //如果密文长度大于模长则要分组解密
        return new String(decipher(Cipher.DECRYPT_MODE, priKey, Base64.decode(decryptData), keyLen));
    }

    /**
     * 数据加密解密
     * @param mode      Cipher.ENCRYPT_MODE:加密、Cipher.DECRYPT_MODE:解密
     * @param key       PublicKey:公钥   PrivateKey:私钥
     * @param data      加密或解密的字节数组
     * @param keyLen    模唱
     * @return          加密或解密的字节数组
     */
    private static byte[] decipher(int mode, Key key, byte[] data,int keyLen) {
        try {
            // 数据加密解密
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA_ECB);
            cipher.init(mode, key);

            // -------------------------------------------------
            // 分段解密
            // -------------------------------------------------
            byte[] enBytes = null;
            for (int i = 0; i < data.length; i += keyLen) {
                // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,i + keyLen));
                enBytes = ArrayUtils.addAll(enBytes, doFinal);
            }
            return enBytes;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("不存在的解密算法");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("无效的补位算法");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("无效的块大小");
        } catch (BadPaddingException e) {
            throw new RuntimeException("补位算法异常");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的私钥");
        }
    }



    /**
     * 用私钥对信息生成数字签名
     * @param privateKey 私钥(BASE64编码)
     * @param data       加密字符串
     * @param signAlg    [SHA256withRSA / SHA1withDSA / MD2withRSA /MD5withRSA /SHA1withRSA]
     * @return           数字签名
     */
    public static String sign(String privateKey, String data, String signAlg){
        try {
            // 通过解码私钥(Base64编码)获取私钥
            PrivateKey priKey = privateKey(Base64.decode(privateKey));
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(signAlg);
            signature.initSign(priKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(signature.sign(),false);
        } catch (Exception  e) {
            throw new RuntimeException("生成数字签名异常");
        }
    }


    /**
     * 用私钥对信息生成数字签名 默认SHA256withRSA
     * @param privateKey 私钥(BASE64编码)
     * @param data       加密字符串
     * @return           数字签名
     */
    public static String sign(String privateKey, String data){
        return sign(privateKey, data, ALGORITHM_RSA_SIGN);
    }


    /**
     * 校验数字签名
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @param data      验签加密内容
     * @param signAlg   [SHA256withRSA / SHA1withDSA / MD2withRSA /MD5withRSA /SHA1withRSA]
     * @return Boolean  校验成功返回true 失败返回false
     */
    public static Boolean verifySign(String publicKey, String sign, String data, String signAlg){
        try {
            // 取公钥匙对象
            PublicKey pubKey = publicKey(Base64.decode(publicKey));
            Signature signature = Signature.getInstance(signAlg);
            signature.initVerify(pubKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            throw new RuntimeException("生成数字签名异常");
        }
    }

    /**
     * 验证数字签名 默认SHA256withRSA
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @param data      验签加密内容
     * @return          校验成功返回true 失败返回false
     */
    public static Boolean verifySign(String publicKey, String sign, String data ){
        return verifySign(publicKey, sign, data, ALGORITHM_RSA_SIGN);
    }


    /**
     * 数字签名 默认accsii排序
     * @param privateKey 私钥
     * @param params 参数
     * @return
     */
    public static String createSign(String privateKey,Map<String, Object> params) {
        // 声明排序集合
        SortedMap<Object, Object> parameters = new TreeMap<>();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String k = entry.getKey();
            Object v = entry.getValue();
            parameters.put(k, v);
        }
        // 声明可变字符串
        StringBuffer sb = new StringBuffer();
        // 参数按照accsii排序（升序）
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        // 循环数据
        for (Map.Entry<Object, Object> entry : es) {
            // 获取K值
            String k = (String) entry.getKey();
            // 获取V值
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) ) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        String sign = sign(privateKey, sb.toString().substring(0,sb.toString().length() -1));
        return sign;
    }

    /**
     * 测试加密功能
     * @param args
     */
    public static void main(String[] args) {
        // 生成公钥和私钥
        Map<String, String> keyMap =genKeyPair();
        String pubKey =  keyMap.get(PUBLIC_KEY);
        String priKey = keyMap.get(PRIVATE_KEY);

        System.out.printf("公钥:%n%s%n私钥:%n%s%n", pubKey, priKey);

        String data = "{\"merchant_order_id\":\"30l5gg2m1shh9o4ktm0oa6baaj\",\"buyer_title\":\"测试个人\",\"buyer_title_type\":1,\"user_phone\":\"13320986077\",\"user_email\":\"1259900@qq.com\",\"invoice_type\":\"026\",\"timestamp\":1639637123,\"seller_info\":[{\"seller_tax_code\":\"915101314508685362\",\"money\":\"11.21\",\"items\":[{\"line_no\":\"1\",\"name\":\"水费\",\"tax_code\":\"09909090999\",\"tax_rate\":\"0.6\",\"tax_type\":\"水费\",\"amount\":\"11.21\",\"num\":\"1\",\"price\":\"11.21\"}]}]}";
        System.out.printf("原文:%n%s%n" , data);

        String ciphertext = encryptByPublicKey(pubKey, data);
        System.out.printf("公钥加密:%n%s%n" , ciphertext);

        System.out.printf("私钥解密:%n%s%n" , decryptByPrivateKey(priKey, ciphertext));

        String ciphertext2 = encryptByPrivateKey(priKey, data);
        System.out.printf("私钥加密:%n%s%n" , ciphertext2);

        System.out.printf("公钥解密:%n%s%n" , decryptByPublicKey(pubKey, ciphertext2));

        String sha256withrsa= sign(priKey, data);
        System.out.printf("【SHA256WithRSA】%n");
        System.out.printf("发数字签名:%n%s%n" , sha256withrsa);
        System.out.printf("效验数字签名:%n%s%n" , verifySign(pubKey, sha256withrsa, data ));
    }
}
