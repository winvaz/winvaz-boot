package com.icore.winvaz.util.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * @Deciption 3des算法工具类
 * @Author wdq
 * @Create 2019/12/20 11:47
 */
public class DES3Utils {
    private static final String Algorithm = "DESede";//定义 加密算法

    /**
     * 3DES加密
     *
     * @param data
     * @param key 密钥
     *
     * @return
     */
    public static byte[] des3EncodeECB(byte[] data, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(build3DesKey(key));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * 3DES解密
     *
     * @param data
     * @param key
     *
     * @return
     */
    public static byte[] des3DecodeECB(byte[] data, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(build3DesKey(key));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    public static void main(String[] args) {
        String desKey = "c758d7fe2f254447a17e0df9888d9941";

        String data = "c758d7fe2f254447a17e0df9888d9941";
        try {
            byte[] byteStr = DES3Utils.des3EncodeECB(data.getBytes("UTF-8"), desKey);
            String encrypt = ByteProtocolUtils.bytes2HexStr(byteStr);

            System.out.println("encrypt：" + encrypt);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Charset charset = Charset.forName("utf-8");
            String user = new String(DES3Utils.des3DecodeECB(ByteProtocolUtils.hexStr2Bytes("7051b8c5434f44af075e2ae98719ac99"), "hzzk"), charset);
            System.out.println("user:" + user); // tsleasing
            String password = new String(DES3Utils.des3DecodeECB(ByteProtocolUtils.hexStr2Bytes("1282adfcb01bb967de818f37a8d2e42a"), "hzzk"), charset);
            System.out.println("password:" + password); // 8Tsleasing9.,/

            String bytes2HexStr = ByteProtocolUtils.bytes2HexStr("tsleasing".getBytes(charset));
            byte[] des3EncodeECB = DES3Utils.des3EncodeECB(bytes2HexStr.getBytes(charset), "hzzk");
            System.out.println("des3EncodeECB:" + new String(des3EncodeECB, charset));
            System.out.println("bytes2HexStr:" + bytes2HexStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
