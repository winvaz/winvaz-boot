package com.icore.winvaz.util.security;

import java.net.URL;

/**
 * @Deciption 安全二维码工具类
 * @Author wdq
 * @Create 2020/7/23 14:47
 * @Version 1.0.0
 */
public class SecurityQRUtils {

    /**
     * 创建二维码键
     *
     * @param invitationCode 邀请码
     * @return
     */
    public static String createQRKey(String invitationCode) {
        StringBuilder sb= new StringBuilder();
        sb.append(invitationCode);
        sb.append("_");
        sb.append(System.currentTimeMillis());
        sb.append("_NB");
        return sb.toString();
    }

    /**
     * 是否超时
     *
     * @param oldCode
     * @return
     */
    public static boolean isTimeOut(String oldCode) {
        boolean isOut = true;
        String[] codes = oldCode.split("_");
        String old_time_str = codes[1];
        long old_time = Long.parseLong(old_time_str);
        long now_time = System.currentTimeMillis();

        if (now_time > old_time && (now_time - old_time) < ((10 ) * 1000)) {
            isOut = false;
        }
        return isOut;
    }

    /**
     * 二维码DES3加密
     * @param data
     * @param desKey
     * @return
     */
    public static String encrypt(String data, String desKey) {
        String encrypt = data;
        try {
            // 保护密钥
            String protectiveKey = "c758d7fe2f254447a17e0df9888d9941";
            // 保护密钥与公钥解密
            byte[] bytes = DES3Utils.des3DecodeECB(ByteProtocolUtils.hexStr2Bytes(desKey), protectiveKey);
            desKey = new String(bytes, "UTF-8");

            // 3DES加密
            byte[] des3EncodeECB = DES3Utils.des3EncodeECB(data.getBytes("UTF-8"), desKey);

            encrypt = Base64Util.encode(des3EncodeECB);
        } catch (Exception ex) {
            encrypt = data;
            ex.printStackTrace();
        }
        return encrypt;
    }

    /**
     * 二维码DES3解密
     * @param data
     * @return
     */
    public static String decrypt(String data, String desKey) {
        String decrypt = data;
        try {
            // 保护密钥
            String protectiveKey = "c758d7fe2f254447a17e0df9888d9941";
            // 保护密钥与公钥解密
            byte[] bytes = DES3Utils.des3DecodeECB(ByteProtocolUtils.hexStr2Bytes(desKey), protectiveKey);
            desKey = new String(bytes, "UTF-8");

            // 3DES解密
            byte[] decodeBuffer = Base64Util.decode(data);
            byte[] des3DecodeECB = DES3Utils.des3DecodeECB(decodeBuffer, desKey);

            decrypt = new String(des3DecodeECB, "UTF-8");
        } catch (Exception ex) {
            decrypt = data;
            ex.printStackTrace();
        }
        return decrypt;
    }

    public static void main(String[] args) throws Exception{
        // 金额密钥
        String key = "cf2fe1df2a45d7fab83e4c730985105abce0d89ad3163d32947d4b094798939fe483f0bf34b192a9";
        // 平台密钥
        //String key = "b95fc2cb3e2744590baeb35647ad150d6c52458bd345bc9e55826dc844c55b1f65d8c916da54997e";
        // 明文
        String qrKey = SecurityQRUtils.createQRKey("e9soaply你");
        System.out.println("qrkey=" + qrKey);

        // 密文
        String encrypt = SecurityQRUtils.encrypt(qrKey, key);
        System.out.println("encrypt:" + encrypt);

        // 等待
        //Thread.sleep(2000);

        // 解密
        String decrypt = SecurityQRUtils.decrypt(encrypt, key);
        System.out.println("decrypt:" + decrypt);

        // 超时
        boolean timeOut = SecurityQRUtils.isTimeOut(decrypt);
        System.out.println(timeOut);

        String s = "http://upload.cs.xundatong.net/85lop845_1542960101034_ND";
        URL url = new URL(s);
        // 处理二维码连接
        String path = url.getPath();
        System.out.println(path);
        path = path.substring(1);
        System.out.println(path);
    }
}