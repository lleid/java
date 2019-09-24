package com.leo.extension18;

import java.time.*;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.lengthOfMonth());
        System.out.println(localDate.isLeapYear());
        System.out.println("-------------------------------");

        MonthDay monthDay = MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
        System.out.println(monthDay);
        MonthDay monthDay2 = MonthDay.from(LocalDate.now());
        System.out.println(monthDay2);
        System.out.println("-------------------------------");

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());

        LocalDateTime l2 = localDateTime.plusMinutes(20).plusSeconds(10);
        System.out.println(l2);
        System.out.println("-------------------------------");

        LocalDate date1 = LocalDate.parse("2017-09-09");//只能转换yyyy-MM-dd格式
        System.out.println(date1);

        System.out.println("-------------------------------");

        Clock clock = Clock.systemDefaultZone();//获取当前时区
        System.out.println(clock.millis());//当前毫秒数

        System.out.println("-------------------------------");

        LocalDate ld1 = LocalDate.of(2018, 2, 14);
        LocalDate ld2 = LocalDate.of(2019, 9, 26);
        System.out.println(ld1.isBefore(ld2));
        System.out.println(ld1.isAfter(ld2));
        System.out.println(ld1.isEqual(ld2));
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getDays());
    }
}
