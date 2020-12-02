package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date(日期类)：
 * * 构造方法：
 * * 	Date() :创建的是当前时间，根据本地系统时间
 * *  Date(long date) ：根据毫秒值返回日期对象
 * *
 * * 一般方法：
 * * 	String toString() ：重写了
 * * 	long getTime() ：返回毫秒值
 * *  before(Date when) /after(Date when) :判断日期的前后
 * *  equals(): 重写了
 */
public class DateTest {
    /**
     * DateFormat: 日期格式化类
     * * 该类为抽象类，只能通过多态创建其子类对象
     * * 构造方法：
     * * SimpleDateFormat(): 使用默认模式创建的匹配器
     * * SimpleDateFormat(String pattern)：使用指定模式创建的匹配器
     * *  一般方法：
     * *  	format()：将Date按照默认的格式生成字符串
     */
    @Test
    public void test() throws ParseException {
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        // 调用方法format方法，对日期格式化
        String format = dateTimeInstance.format(new Date());
        System.out.println("format格式化输出：" + format);

        String s = "2018-12-05";
        // 使用静态方法，类名调用getDateInstance()返回DateFormat类的子类对象SimpleDateFormat
        DateFormat dateInstance = DateFormat.getDateInstance();
        //DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
        System.out.println("parse格式化输出：" + dateInstance.parse(s));

        Date date = new Date();
        System.out.println("Date日期直接输出：" + date); // Tue Apr 14 11:27:04 CST 2020
        System.out.println("SimpleDate格式化直接输出：" + new SimpleDateFormat().format(date)); // 20-4-14 上午11:54
        // 把日期对象，转成毫秒值
        long time = date.getTime();
        System.out.println("Date的毫秒值：" + time);
        // 获取当前时间的毫秒值
        System.out.println("当前时间的毫秒值" + System.currentTimeMillis());
        // 将毫秒值转成日期对象
        date.setTime(time);
        System.out.println("将毫秒值转成日期对象" + date); // Mon Jun 10 17:37:58 CST 2019 需要格式化
        System.out.println("Date构造函数毫秒转日期对象: " + new Date(time));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(date);
        System.out.println(format1); // 2019-06-10 17:40:05
        System.out.println("=======");
        // runNian();
        // baoZhi();
        System.out.println("======");
        int i = 10;
        DateTest.setValue(i);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        DateTest.setValue(cal);
        System.out.print(i + "-" + cal.get(Calendar.YEAR));
    }

    /**
     * Calendar类：
     * * 	日历类
     * * 构造：使用Calendar rightNow = Calendar.getInstance();获取一个日历类
     * * 		在创建一个日期类时：月份0-11.
     * *
     * * 一般方法：
     * * 	toString():重写了
     * * 	get()： 使用该方法传入要获取的字段（信息：年，月，日）。
     * * 	Date getTime():获取该日历的日期类
     * *  Long getTimeInMillis() ：:获取该日历的毫秒值
     * *  add(int 字段,int 加上的值):
     */
    //定义方法，实现闰年的计算
    @Test
    public void runNian() {
        /*
         * 计算闰年，2月的最后一天是什么，如果是28平年，29闰年
         * 获取日历对象getInstance
         * 已知年份
         * 将日历设置到这一年的3月1日
         * 向前偏移1天，获取天数判断
         */
        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, 2, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        /*
        if (calendar.get(Calendar.DAY_OF_MONTH) == 29) {
            System.out.println("闰年");
        } else {
            System.out.println("平年");
        }
        */
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }

    //定义方法，实现保质期的计算
    @Test
    public void baoZhi() {
        /*生产日期是2014-3-10，保质期是187天，那一天到期
         * 获取日历获取对象--操作系统走
         * set方法，将日历设置到生产日期上
         * 调用add方法，让天数，偏移187天，输出日历
         */
        String date = "2020-07-10";
        DateFormat dateInstance = DateFormat.getDateInstance();
        Date parse = null;
        try {
            parse = dateInstance.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();

        // set方法，将日历设置到生产日期
        c.setTime(parse);
        // c.set(2014, 2, 10);
        // // 调用add方法，让天数偏移180天，输出日历
        c.add(Calendar.DAY_OF_MONTH, 183);
        // 打印
        printCalendar(c);
    }

    @Test
    public void test1() {
        // 格式化日期输出
        // System.out.printf("%1$s %2$tB %2$te, %2$tY", "now date:", new Date()); // 八月 31, 2020
        System.out.printf("%s %tB %<te, %<tY", "now date:", new Date()); // 八月 31, 2020
    }

    //打印当前日历对象的信息
    private static void printCalendar(Calendar c) {
        System.out.println(c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月" +
                c.get(Calendar.DAY_OF_MONTH) + "日 星期" + (c.get(Calendar.DAY_OF_WEEK) - 1));
    }

    public static void setValue(int value) {
        value++;
    }

    public static void setValue(Calendar value) {
        value.set(Calendar.YEAR, 2009);
    }

}

