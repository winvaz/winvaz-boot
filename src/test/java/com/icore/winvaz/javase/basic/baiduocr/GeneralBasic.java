package com.icore.winvaz.javase.basic.baiduocr;

import cn.hutool.core.io.IoUtil;
import com.icore.winvaz.util.security.Base64Util;
import com.icore.winvaz.util.security.FileUtil;
import com.icore.winvaz.util.web.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 通用文字识别
 */
public class GeneralBasic {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     *
     */
    public static String generalBasic() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        try {
            // 本地文件路径
            String filePath = "/Users/wdq/Desktop/2.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encodes(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam + "&recognize_granularity=samll";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.d2b4eed02ac07cec1d02513c7318f46b.2592000.1626946722.282335-24419131";

            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            String wordsResult = jsonObject.getString("words_result");
            System.out.println(wordsResult);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GeneralBasic.generalBasic();
    }
}