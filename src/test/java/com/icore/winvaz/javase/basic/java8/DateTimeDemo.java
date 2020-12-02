package com.icore.winvaz.javase.basic.java8;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @Deciption Java 8 新特性--Date-Time
 * @Author wdq
 * @Create 2019-10-12 14:07
 */
public class DateTimeDemo {
    public static void main(String[] args) {

        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间:" + localDateTime);
        System.out.println("当前时间加一天" + localDateTime.plusDays(-7));

        /*
        // 当前日期
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("当前日期:" + localDate);

        // 月 日 秒
        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        int second = localDateTime.getSecond();
        System.out.println("月：" + month + "，日：" + day + "，秒：" + second);
        */

        // 使用时区时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("时区时间：" + now);

        // 获取zone
        ZoneId zone = now.getZone();
        System.out.println("时区ID：" + zone);

        // 获取offset
        ZoneOffset offset = now.getOffset();
        System.out.println("offset：" + offset);
    }

    /**
     * 打印当前月日历
     * @author wdq
     * @create 2020/9/1 18:28
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void test() {
        // 获取当前日期
        LocalDate date = LocalDate.now();
        // 获取月份
        int month = date.getMonthValue();
        // 获取天数
        int toDay = date.getDayOfMonth();

        // set start of month
        date = date.minusDays(toDay - 1);
        // 获取当前星期几
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // 1=Monday .... 7=Sunday
        int value = dayOfWeek.getValue();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < value; i++) {
            System.out.println("  ");
        }
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == toDay) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}
