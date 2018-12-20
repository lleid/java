package com.leo.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 可限时
 */
public class ReentrantLockTryThread extends Thread {

    private static ReentrantLock lock = new ReentrantLock();

    public ReentrantLockTryThread(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        try {
            System.out.println("current thread :" + this.getName());
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(this.getName() + " sleeping 6s");
                Thread.sleep(6000);
            } else {
                System.out.println(this.getName() + " get lock failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + ".isHeldByCurrentThread");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTryThread j1 = new ReentrantLockTryThread("j1");
        ReentrantLockTryThread j2 = new ReentrantLockTryThread("j2");

        j1.start();
        j2.start();
    }
}
