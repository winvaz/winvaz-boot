package com.icore.winvaz.util.security;

import org.apache.shiro.crypto.hash.SimpleHash;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Deciption SHA256算法工具类
 * @Author wdq
 * @Create 2019/12/23 15:38
 */
public class SHA256Util {

    // ##################### Shiro #####################
    /**
     * @Description 私有构造器
     * @author wdq
     * @create 2019/12/23 17:29
     * @param
     * @Return
     * @exception
     */
    public SHA256Util() {

    }

    /**  加密算法 **/
    public final static String HASH_ALGORITHM_NAME = "SHA-256";
    /**  循环次数 **/
    public final static int HASH_ITERATIONS = 15;

    /**  执行加密-采用SHA256和盐值加密 **/
    public static String sha256(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

    // #######################################

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacSHA256";

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public  static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }

        return hs.toString().toLowerCase();
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptHMAC(String data, String key) {
        String hash = "";
        try {
            Mac mac = Mac.getInstance(KEY_MAC);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
            mac.init(secretKey);

            byte[] bytes = mac.doFinal(data.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hash;
    }

}