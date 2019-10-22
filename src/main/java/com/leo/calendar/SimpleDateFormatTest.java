package com.leo.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * SimpleDateFormat 非线程安全，多个线程调用时，会抛 NumberFormatException: multiple points
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new InnerStaticSimpleDateFormat(), "测试线程").start(); //java.lang.NumberFormatException: multiple points
        }
    }

    public static class InnerStaticSimpleDateFormat implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + ":" + DateUtils.parse("2018-09-09"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class DateUtils {

    private static volatile SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static String format(Date date) {
        return sdf.format(date);
    }

    public static Date parse(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
