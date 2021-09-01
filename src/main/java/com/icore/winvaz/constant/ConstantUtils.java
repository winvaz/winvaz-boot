package com.icore.winvaz.constant;

/**
 * 获取枚举工具类
 *
 * @Author wdq
 * @Create 2021/7/9 17:21
 * @Version 1.0.0
 */
public class ConstantUtils {

    /**
     * 根据枚举的code获取desc
     *
     * @param <T>
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends Enum<T> & BaseConstant<T>> String getEnumName(Class<T> clazz,
                                                                           Object code) {
        T[] enums = clazz.getEnumConstants();
        for (T obj : enums) {
            if (obj.getCode().equals(code)) {
                String result = obj.getName();
                return result;
            }
        }
        return null;
    }

    /**
     * 根据枚举的code获取整个枚举对象
     *
     * @param <T>
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends Enum<T> & BaseConstant<T>> BaseConstant<T> getEnum(Class<T> clazz,
                                                                                Object code) {
        T[] enums = clazz.getEnumConstants();
        for (T obj : enums) {
            if (obj.getCode().equals(code)) {
                return obj;
            }
        }
        return null;
    }
}
