package com.leo.concurrent;

public class DeadLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("thread1 get lock1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("thread1 get lock1");
                }

                System.out.println("thread1 end");
            }
        }, "thread1").start();


        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("thread2 get lock2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("thread2 get lock1");
                }

                System.out.println("thread2 end");
            }
        }, "thread2").start();
    }
}
