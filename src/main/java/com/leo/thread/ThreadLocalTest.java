package com.leo.thread;


import com.leo.extension18.lambda.beans.User;

import java.util.stream.IntStream;

/**
 * 线程本地变量，线程对变量的操作不会影响其他线程
 * 但是如果线程的执行周期过长可能导致内存泄漏的分享，可以调用remove()加快内存的释放
 * 使用场景:数据库连接和用户session管理
 */
public class ThreadLocalTest {

    private static ThreadLocal<User> tl = new ThreadLocal<User>() {
        @Override
        protected User initialValue() {
            return new User("tom", 12);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 3).forEach(i -> {
            new TestThread().start();
        });

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "," + tl.get());
    }

    static class TestThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                User user = tl.get();

                user.setName(Thread.currentThread().getName());
                user.setAge(i);

                tl.set(user);
                System.out.println(tl.get());
            }
        }
    }
}
