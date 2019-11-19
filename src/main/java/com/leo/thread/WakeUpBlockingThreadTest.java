package com.leo.thread;

import java.util.concurrent.TimeUnit;

/**
 * 唤醒阻塞中的线程
 */
public class WakeUpBlockingThreadTest {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println("thread1 is sleeping");
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("thread1 interrupt");
        }, "thread1").start();

        new Thread(() -> {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("thread2 is running");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "thread2").start();


        ThreadGroup group = Thread.currentThread().getThreadGroup();

        int count = group.activeCount();//获取活动的线程
        Thread[] threads = new Thread[count];
        group.enumerate(threads);

        for (Thread thread : threads) {
            if ("thread1".equals(thread.getName())) {
                thread.interrupt();
            }
        }
    }
}


