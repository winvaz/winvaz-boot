package com.icore.winvaz.javase.basic.baiduocr;

import com.alibaba.fastjson.JSONArray;
import com.icore.winvaz.util.security.Base64Util;
import com.icore.winvaz.util.security.FileUtil;
import com.icore.winvaz.util.web.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;

/**
 * @Author wdq
 * @Create 2021/6/22 17:21
 * @Version 1.0.0
 */

public class App {
    public static void main(String[] args) throws Exception {
        /**
         * 重要提示代码中所需工具类 业务类型 接受
         * FileUtil,Base64Util,HttpUtil,GsonUtils请从
         * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
         * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
         * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
         * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
         * 下载
         * 模板ID为：759d2a8cb59a6f8cb23902a35f0faa7f
         */
        // iocr识别apiUrl
        String recogniseUrl = "https://aip.baidubce.com/rest/2.0/solution/v1/iocr/recognise";


        String filePath = "/Users/wdq/Desktop/WechatIMG1463.jpeg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 请求模板参数
            String recogniseParams = "templateSign=52519c37cd48e61db18fb539005ce628&image=" + URLEncoder.encode(imgStr, "UTF-8");
            // 请求分类器参数
            String classifierParams = "classifierId=your_classfier_id&image=" + URLEncoder.encode(imgStr, "UTF-8");


            String accessToken = "24.58d382375231f95f99c2a47176ae3e48.2592000.1626947071.282335-24370806";
            // 请求模板识别
            String result = HttpUtil.post(recogniseUrl, accessToken, recogniseParams);
            // 请求分类器识别
            // String result = HttpUtil.post(recogniseUrl, accessToken, classifierParams);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            System.out.println("data=" + jsonObject1);
            String ret = null;
            try {
                ret = jsonObject.getString("ret");
            } catch (JSONException e) {
                if ("272000".equals(jsonObject.get("error_code").toString())) {
                    System.out.println(jsonObject.get("error_msg").toString());
                }
            }


            List<One> ones = JSONArray.parseArray(ret, One.class);
            ones.forEach(x -> {
                System.out.println(x.getWord_name() + "==" + x.getWord());
            });
            // System.out.println(ones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}