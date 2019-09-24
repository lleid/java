package com.leo.joda;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class JodaTimeTest {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        System.out.println(today.toString("yyyy-MM-dd HH:mm"));

        DateTime tomorrow = today.plusDays(1);
        System.out.println(tomorrow.toString("yyyy-MM-dd HH:mm"));

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd HH:mm"));

        LocalDate localDate = new LocalDate();
        System.out.println("当前日期三个月后的最后一天:" + localDate.plusMonths(3).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"));

    }
}
