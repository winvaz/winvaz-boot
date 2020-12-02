package com.icore.winvaz.util.common;

import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @Deciption 日期工具类
 * @Author wdq
 * @Create 2020/5/20 17:34
 * @Version 1.0.0
 */
public class LocalDateTimeUtils {
    /**
     * 常量字段
     */
    public static final String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD_HH_MM_SS_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
    public static final String YYYYMMDD_PATTERN = "yyyy/MM/dd";
    public static final String YYYYMMDDHHMMSS_PATTERN = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSS_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String YYYYMMDDHHMMSS_DATE_PATTERN = "yyyy年MM月dd日";

    /**
     * 获取某年的第一天
     *
     * @param year 年
     * @throws
     * @author wdq
     * @create 2020/5/20 17:59
     * @Return java.time.LocalDate
     */
    public static LocalDate getYearFirstDay(Integer year) {
        return LocalDate.of(year, 1, 1);
    }

    /**
     * 获取某月的第一天
     *
     * @param year
     * @throws
     * @author wdq
     * @create 2020/5/20 18:00
     * @Return java.time.LocalDate
     */
    public static LocalDate getMonthFirstDay(Integer year, Integer month) {
        return LocalDate.of(year, month, 1);
    }

    /**
     * 获取时间月的第一天
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/20 18:16
     * @Return java.time.LocalDate
     */
    public static LocalDate getDateMonthFirstDay(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), localDate.getDayOfMonth(), 1);
    }

    /**
     * 获取时间月的最后一天
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/20 18:19
     * @Return java.time.LocalDate
     */
    public static LocalDate getDateMonthLastDay(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取某年某季度的第一天
     * 第一季度：1.1
     * 第二季度：4.1
     * 第三季度：7.1
     * 第四季度：10.1
     *
     * @param year
     * @param season
     * @throws
     * @author wdq
     * @create 2020/5/20 18:21
     * @Return
     */
    public static LocalDate getSeasonFirstDay(Integer year, Integer season) {
        return LocalDate.of(year, ((season - 1) * 3) + 1, 1);
    }

    /**
     * 获取某年某季度的第一天
     * 第一季度：1.1
     * 第二季度：4.1
     * 第三季度：7.1
     * 第四季度：10.1
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/20 18:21
     * @Return
     */
    public static LocalDate getSeasonFirstDay(LocalDate localDate) {
        int monthValue = localDate.getMonthValue();
        return LocalDate.of(localDate.getYear(), monthValue % 3 == 0 ? (monthValue / 3) : (monthValue / 3) + 1, 1);
    }

    /**
     * 获取时间为准的本周第一天
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/20 18:35
     * @Return java.time.LocalDate
     */
    public static LocalDate getDateWeekFirstDay(LocalDate localDate) {
        return localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1);
    }

    /**
     * 获取时间为准的本周最后一天
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/20 18:35
     * @Return java.time.LocalDate
     */
    public static LocalDate getDateWeekLastDay(LocalDate localDate) {
        return localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7);
    }

    /**
     * 是否在两个日期之间>= <=
     *
     * @param calcDate
     * @param startDate
     * @param endDate
     * @throws
     * @author wdq
     * @create 2020/5/25 16:01
     * @Return boolean
     */
    public static boolean isMiddleOfLocalDate(LocalDate calcDate, LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(calcDate)
                || Objects.isNull(startDate)
                || Objects.isNull(endDate)) {
            return false;
        }
        long startDiffDay = startDate.until(calcDate, ChronoUnit.DAYS);
        long endDiffDay = endDate.until(calcDate, ChronoUnit.DAYS);
        if (startDiffDay >= 0 && endDiffDay <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否在两个日期时间之间>= <=
     *
     * @param calcDateTime
     * @param startDateTime
     * @param endDateTime
     * @throws
     * @author wdq
     * @create 2020/5/25 16:01
     * @Return boolean
     */
    public static boolean isMiddleOfLocalDateTime(LocalDateTime calcDateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (Objects.isNull(calcDateTime)
                || Objects.isNull(startDateTime)
                || Objects.isNull(endDateTime)) {
            return false;
        }
        long startDiffDay = startDateTime.until(calcDateTime, ChronoUnit.SECONDS);
        long endDiffDay = endDateTime.until(calcDateTime, ChronoUnit.SECONDS);
        if (startDiffDay >= 0 && endDiffDay <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 两个日期相差天数
     *
     * @param startDate
     * @param endDate
     * @throws
     * @author wdq
     * @create 2020/5/25 16:07
     * @Return long
     */
    public static long diffDayOfLocalDate(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 两个日期时间相差秒数
     *
     * @param startDateTime
     * @param endDateTime
     * @throws
     * @author wdq
     * @create 2020/5/25 16:07
     * @Return long
     */
    public static long diffSecondOfLocalDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.until(endDateTime, ChronoUnit.SECONDS);
    }

    /**
     * 比较两个日期大小
     *
     * @param startDate
     * @param endDate
     * @throws
     * @author wdq
     * @create 2020/5/25 16:11
     * @Return int
     */
    public static int compareToOfLocalDate(LocalDate startDate, LocalDate endDate) {
        return startDate.compareTo(endDate);
    }

    /**
     * 比较两个日期大小
     *
     * @param startDateTime
     * @param endDateTime
     * @throws
     * @author wdq
     * @create 2020/5/25 16:11
     * @Return int
     */
    public static int compareToOfLocalDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.compareTo(endDateTime);
    }

    /**
     * 字符串日期转成LocalDate
     *
     * @param ymd
     * @throws
     * @author wdq
     * @create 2020/5/21 14:03
     * @Return java.time.LocalDate
     */
    public static LocalDate parseShortStringToLocalDate(String ymd, String pattern) {
        if (StringUtils.isEmpty(ymd)) {
            return null;
        }

        if (StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_PATTERN;
        }
        return LocalDate.parse(ymd, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串日期时间转成LocalDateTime
     *
     * @param ymd
     * @throws
     * @author wdq
     * @create 2020/5/21 14:03
     * @Return java.time.LocalDate
     */
    public static LocalDateTime parseLongStringToLocalDateTime(String ymd, String pattern) {
        if (StringUtils.isEmpty(ymd)) {
            return null;
        }

        if (StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_HH_MM_SS_PATTERN;
        }
        return LocalDateTime.parse(ymd, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 正则匹配将字符串日期转成LocalDate
     */
    public static LocalDate parseRegexStringToLocalDate(String ymd) {
        if (StringUtils.isEmpty(ymd)) {
            return null;
        }
        String[] splitArr = ymd.split(" ");
        if (splitArr.length >= 1) {
            ymd = splitArr[1];
        }
        String[] split = ymd.split("-");
        if (split.length < 3) {
            return null;
        }
        return LocalDate.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
    }

    /**
     * 正则匹配将字符串日期转成LocalDate
     */
    public static LocalDate parseRegexStringToLocalDate(String ymd, String regex) {
        if (StringUtils.isEmpty(ymd)) {
            return null;
        }
        String[] splitArr = ymd.split(" ");
        if (splitArr.length >= 1) {
            ymd = splitArr[1];
        }
        String[] split = ymd.split(regex);
        if (split.length < 3) {
            return null;
        }
        return LocalDate.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
    }

    /**
     * LocalDate转String
     *F
     * @param localDate
     * @param pattern
     * @throwF
     * @author wdq
     * @create 2020/5/21 15:16
     * @Return java.lang.String
     */
    public static String parseLocalDateToString(LocalDate localDate, String pattern) {
        if (Objects.isNull(localDate)) {
            return "";
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_PATTERN;
        }
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDateTime转String
     *
     * @param localDateTime
     * @param pattern
     * @throws
     * @author wdq
     * @create 2020/5/21 15:16
     * @Return java.lang.String
     */
    public static String parseLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_HH_MM_SS_PATTERN;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将Date转成LocalDate
     *
     * @param date
     * @throws
     * @author wdq
     * @create 2020/5/25 16:17
     * @Return java.time.LocalDate
     */
    public static LocalDate parseDateToLocalDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将Date转成LocalDateTime
     *
     * @param date
     * @throws
     * @author wdq
     * @create 2020/5/25 16:17
     * @Return java.time.LocalDateTime
     */
    public static LocalDateTime parseDateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将LocalDate转成Date
     *
     * @param localDate
     * @throws
     * @author wdq
     * @create 2020/5/25 16:20
     * @Return java.util.Date
     */
    public static Date parseLocalDateToDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将LocalDateTime转成Date
     *
     * @param localDateTime
     * @throws
     * @author wdq
     * @create 2020/5/25 16:20
     * @Return java.util.Date
     */
    public static Date parseLocalDateToDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取两个持续时间之差的毫秒值
     *
     * @param startDateTime 开始时间(减数)
     * @param endDateTime   结束时间(被减数)
     * @throws
     * @author wdq
     * @create 2020/5/25 16:30
     * @Return long
     */
    public static long millisOfLocalDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Instant start = startDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant end = endDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Duration.between(start, end).toMillis();
    }

    /**
     * 比较第一个日期是否小于第二个日期
     * @param startDate 第一个日期
     * @param endDate 第二个日期
     * @return true-小于;false-大于
     */
    public static boolean localDateIsBefore(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }


    /**
     * 比较第一个日期是否大于第二个日期
     * @param startDate 第一个日期
     * @param endDate 第二个日期
     * @return true-大于;false-不大于
     */
    public static boolean localDateIsAfter(LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(endDate);
    }

    /**
     * 比较两个日期是否相等
     * @param startDate 第一个日期
     * @param endDate 第二个日期
     * @return true-相等;false-不相等
     */
    public static boolean localDateIsEqual(LocalDate startDate, LocalDate endDate) {
        return startDate.isEqual(endDate);
    }

    /**
     * 比较第一个时间是否小于第二个时间
     *
     * @param startDateTime 第一个时间
     * @param endDateTime   第二个时间
     * @return true-小于;false-大于
     */
    public static boolean localDateTimeIsBefore(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.isBefore(endDateTime);
    }
    /**
     * 比较第一个时间是否大于第二个时间
     *
     * @param startDateTime 第一个时间
     * @param endDateTime   第二个时间
     * @return true-小于;false-大于
     */
    public static boolean localDateTimeIsAfter(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.isAfter(endDateTime);
    }

    /**
     * 比较两个时间是否相等
     * @param startDateTime 第一个时间
     * @param endDateTime 第二个时间
     * @return true-相等;false-不相等
     */
    public static boolean localDateIsEqual(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.isEqual(endDateTime);
    }


    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.println(now.toLocalDate());
        System.out.println(now.toLocalTime());
        System.out.println(now.toLocalDateTime());
        System.out.println(now);
    }
}