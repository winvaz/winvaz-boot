package com.icore.winvaz.util.security;

import cn.hutool.Hutool;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @Deciption 对个人信息脱敏
 * @Author wdq
 * @Create 2020/11/25 18:05
 * @Version 1.0.0
 */
public class DesensitizationUtil {

    /**
     * 星号
     */
    private static final String ASTERISK_PATTERN = "*";

    /**
     * 三个名字脱敏正则
     */
    private static final String THREE_NAME_PATTERN = "(?<=[\\u4e00-\\u9fa5]).*(?=[\\u4e00-\\u9fa5])";

    /**
     * 两个个名字脱敏正则
     */
    private static final String TWO_NAME_PATTERN = ".*(?=[\\u4e00-\\u9fa5])";

    /**
     * 15位身份证脱敏正则
     */
    private static final String FIFTEEN_ID_PATTERN = "(\\w{6})\\w*(\\w{3})";

    /**
     * 18位身份证脱敏正则
     */
    private static final String EIGHTEEN_ID_PATTERN = "(\\w{6})\\w*(\\w{3})";

    /**
     * 手机号脱敏正则
     */
    private static final String MOBILE_PATTERN = "(\\w{3})\\w*(\\w{4})";

    /**
     * 车牌号脱敏正则
     */
    private static final String PLATE_PATTERN = "(\\w{2})\\w*(\\w{2})";

    /**
     * 邮箱脱敏正则
     */
    private static final String EMAIL_PATTERN = "(\\w+)\\w{3}@(\\w+)";

    /**
     * 数字正则
     */
    private static final String NUMBER_PATTERN = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";

    /**
     * 姓名右边脱敏
     *
     * @param name
     * @throws
     * @create 2020/11/25 18:54
     * @Return 张**
     */
    public static String desenRightName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            String left = StringUtils.left(name, 1);
            return StringUtils.rightPad(left, StringUtils.length(name), ASTERISK_PATTERN);
        }
        return name;
    }

    /**
     * 姓名中间脱敏
     *
     * @param name
     * @throws
     * @create 2020/11/25 18:54
     * @Return 张**三
     */
    public static String desenMiddleName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return name.replaceAll(THREE_NAME_PATTERN, ASTERISK_PATTERN);
        }
        return name;
    }

    /**
     * 姓名左边脱敏
     *
     * @param name
     * @throws
     * @create 2020/11/25 18:54
     * @Return **三
     */
    public static String desenLeftName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return name.replaceAll(TWO_NAME_PATTERN, ASTERISK_PATTERN);
        }
        return name;
    }

    /**
     * 对身份证脱敏
     *
     * @param ID
     * @throws
     * @author wdq
     * @create 2020/11/25 19:03
     * @Return java.lang.String
     */
    public static String desenMiddleID(String ID) {
        if (StringUtils.isNotEmpty(ID)) {
            // 15位身份证
            if (Objects.equals(15, ID.length())) {
                return ID.replaceAll(FIFTEEN_ID_PATTERN, "$1******$2");
            }
            // 18位身份证
            if (Objects.equals(18, ID.length())) {
                return ID.replaceAll(EIGHTEEN_ID_PATTERN, "$1*********$2");
            }
        }
        return ID;
    }

    /**
     * 手机号脱敏
     *
     * @param mobile
     * @throws
     * @author wdq
     * @create 2020/11/26 10:08
     * @Return java.lang.String
     */
    public static String desenMobile(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            return mobile.replaceAll(MOBILE_PATTERN, "$1******$2");
        }
        return mobile;
    }

    /**
     * 车牌号脱敏
     *
     * @param plate
     * @throws
     * @author wdq
     * @create 2020/11/26 10:10
     * @Return java.lang.String
     */
    public static String desenPlate(String plate) {
        if (StringUtils.isNotEmpty(plate)) {
            return plate.replaceAll(PLATE_PATTERN, "$1***$2");
        }
        return plate;
    }

    /**
     * 邮箱脱敏
     *
     * @param email
     * @throws
     * @author wdq
     * @create 2020/11/26 10:20
     * @Return java.lang.String
     */
    public static String desenEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            return email.replaceAll(EMAIL_PATTERN, "$1***@$2");
        }
        return email;
    }

    private static String desenAddress(String address) {
        if (StringUtils.isNotEmpty(address)) {
            // 对地址中的数字星号(*)处理
            address = address.replaceAll(NUMBER_PATTERN, "**");
            // 对中文地址进行星号(*)处理
            return StringUtils.left(address, 3)
                    .concat(
                            StringUtils.leftPad(
                                    StringUtils.right(address, address.length() - 11),
                                    StringUtils.length(address), "*"
                            )
                    );
        }
        return address;
    }

    public static void main(String[] args) {
        System.out.println(desenEmail("winvaz@163.com"));
        System.out.println(desenMiddleName("张三笠"));
    }
}
