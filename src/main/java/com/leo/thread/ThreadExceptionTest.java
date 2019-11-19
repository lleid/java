package com.leo.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程中如果捕获异常，继续执行。如果不捕获异常，直接退出，如果持有某个对象的monitor，那么这个监控器会被释放。
 */
public class ThreadExceptionTest {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                throwException();//不捕获时直接退出
                System.out.println(new Date().getTime());
            }
        }).start();
    }

    public static void throwException() {
        int a = 5 / 0;
    }
}
