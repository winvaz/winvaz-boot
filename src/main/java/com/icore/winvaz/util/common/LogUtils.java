package com.icore.winvaz.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Deciption 全局的日志工具类
 * @Author wdq
 * @Create 2020/1/14 09:02
 * @Version 1.0.0
 */
public class LogUtils {

    public static void info(String msg) {
        logger().info(msg);
    }

    /**
     * @param msg
     * @param objects
     * @throws
     * @Description 占位符方式，优先使用对象的toString方法序列化
     * 若对象没有重写toString使用JSON序列化
     * @author wdq
     * @create 2020/1/14 09:29
     * @Return void
     */
    public static void info(String msg, Object... objects) {
        Logger logger = logger();
        if (logger.isInfoEnabled()) {
            logger.info(msg, objects);
        }
    }

    public static void error(String msg) {
        logger().info(msg);
    }

    public static void error(String msg, Object... objects) {
        logger().error(msg, objects);
    }



    public final static Logger logger() {
        StackTraceElement caller = findCaller();
        if (caller == null) {
            return LoggerFactory.getLogger(LogUtils.class);
        }
        String[] names = caller.getClassName().split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.length - 1; i++) {
            stringBuilder.append(names[i].charAt(0)).append(".");
        }
        stringBuilder.append(names[names.length - 1]);
        return LoggerFactory.getLogger(stringBuilder.toString() + "." + caller.getMethodName() + "() [line:" + caller.getLineNumber() + "]");
    }

    /**
     * @param
     * @throws
     * @Description 获取最原始被调用的堆栈信息
     * @author wdq
     * @create 2020/1/14 09:18
     * @Return java.lang.StackTraceElement
     */
    private static StackTraceElement findCaller() {
        // 获取堆栈信息
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        // 最原始调用的堆栈信息
        StackTraceElement caller = null;
        // 日志类名称
        String logClassName = LogUtils.class.getName();
        // 循环遍历到日志类标识
        boolean isEachLogClass = false;
        // 遍历堆栈信息，获取出最原始调用的方法信息
        for (StackTraceElement stackTraceElement : stackTrace) {
            // 遍历到日志类
            if (logClassName.equals(stackTraceElement.getClassName())) {
                isEachLogClass = true;
            }
            // 下一个非日志类的堆栈，就是最原始被调用的方法
            if (isEachLogClass) {
                if (!logClassName.equals(stackTraceElement.getClassName())) {
                    caller = stackTraceElement;
                    break;
                }
            }
        }
        return caller;
    }

    /**
     * @Description 获取调用ERROR INFO DEBUG静态类的类名
     * @author wdq
     * @create 2020/8/5 21:39
     * @param
     * @Return java.lang.String
     * @exception
     */
    private static String getClassName() {
        return new SecurityManager() {
            public String getClassName() {
                return getClassContext()[3].getName();
            }
        }.getClassName();
    }

    private static Object[] parameterPreHandle(Object[] objects) {
        return null;
    }

    public Object[] preHandle(Object[] objects) {
        int length = objects.length;
        Object[] objs = new Object[length];
        for (int i = 0; i < length; i++) {
            if (null == objects[i]) {
                objs[i] = null;
                continue;
            }
        }
        return null;
    }
}
