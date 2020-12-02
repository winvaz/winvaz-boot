package com.icore.winvaz.javase.basic;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @Deciption 标准Java日志框架
 * <p>
 * java.util.logging包中主要的类有以下几个
 * LogManager    存在一个单一的全局 LogManager 对象，它可用于维护 Logger 和日志服务的一组共享状态。
 * Logger        Logger 对象用来记录特定系统或应用程序组件的日志消息
 * LogRecord     LogRecord 对象用于在日志框架和单个日志 Handler 之间传递日志请求
 * Handler       Handler 对象从 Logger 中获取日志信息，并将这些信息导出
 * Formatter     Formatter 为格式化 LogRecords 提供支持
 * @Author wdq
 * @Create 2020/11/14 14:48
 * @Version 1.0.0
 */
public class LogTest {
    // getLogger()方法创建或获取日记记录器
    private static final Logger logger = Logger.getLogger("com.icore.winvaz.javase.basic");

    public static void main(String[] args) {

        // 取消所有日志
        // Logger.getGlobal().setLevel(Level.OFF);
        // 基本日志，调用全局日志记录器(global logger)并调用其info方法。
        // 十一月 14, 2020 2:52:13 下午 com.icore.winvaz.javase.basic.LogTest main
        // 信息: File->Open menu item selected
        Logger.getGlobal().info("File->Open menu item selected");

        // 日志记录器的层次结构
        /**
         *  有7个级别
         *  SEVRE
         *  WARING
         *  INFO
         *  CONFIG
         *  FINE
         *  FINER
         *  FINEST
         */
        // logger.setLevel(Level.FINE);
        // logger.warning("warning");
        // logger.info("info");
        // logger.log(Level.FINE, "fine");

        // logp()方法获取调用类和方法的确切位置
        // logger.logp(parameters);

        // 有一些用来跟踪执行流的便利方法
        // logger.entering("com.icore.winvaz.javase.basic.LogTest", "main", new Object[]{args});
        // 可变参
        // logger.entering("com.icore.winvaz.javase.basic.LogTest", "main", args);
        // logger.exiting("com.icore.winvaz.javase.basic.LogTest", "main", count);

        // 记录异常描述
        // logger.throwing("com.icore.winvaz.javase.basic.LogTest", "main", IOException);
        // logger.log(Level.FINE, "Hello", IOException);

        // 过滤器，根据日志的记录级别进行过滤
        // 需实现Filter接口并定义以下方法
        // boolean isLoggable(LogRecord record);

        // 格式化器，扩展Formatter类并覆盖下面这个方法
        // String format(LogRecord record);
    }
}
