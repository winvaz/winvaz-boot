package com.icore.winvaz.javase.basic.baiduocr;

/**
 * @Author wdq
 * @Create 2021/6/21 16:14
 * @Version 1.0.0
 */

import com.icore.winvaz.util.security.Base64Util;
import com.icore.winvaz.util.security.FileUtil;
import com.icore.winvaz.util.web.HttpUtil;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 增值税发票识别
 */
public class VatInvoice {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String vatInvoice() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice";
        try {
            // 本地文件路径
            // String filePath = "/Users/wdq/Desktop/1.png";
            // byte[] imgData = FileUtil.readFileByBytes(filePath);
            InputStream is = new FileInputStream(new File("/Users/wdq/Desktop/1.png"));
            byte[] imgData = IOUtils.toByteArray(is);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.e2da9c94d17806535daa2da6f689179b.2592000.1626512906.282335-24370806";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        VatInvoice.vatInvoice();
    }
}