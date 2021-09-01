package com.icore.winvaz.constant;

/**
 * 定义常量推荐类内枚举
 * @Author wdq
 * @Create 2021/7/9 17:18
 * @Version 1.0.0
 */
public class TestContant {

    /**
     * 业务类型
     * @author wdq
     * @create 2021/7/9 17:19
     */
    enum BusinessType implements BaseConstant<String> {
        IMPORT("import", "进口"),
        EXPORT("export", "出口");

        private String code;
        private String name;

        BusinessType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public String getCode() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    }
}
