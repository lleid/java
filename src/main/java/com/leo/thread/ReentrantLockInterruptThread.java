package com.leo.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 可中断
 */
public class ReentrantLockInterruptThread extends Thread {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int i;

    public ReentrantLockInterruptThread(String name, int i) {
        super.setName(name);
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if (i == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            System.out.println("exc :" + Thread.currentThread().getName());
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + ".线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterruptThread j1 = new ReentrantLockInterruptThread("j1", 1);
        ReentrantLockInterruptThread j2 = new ReentrantLockInterruptThread("j2", 2);

        j1.start();
        j2.start();

        Thread.sleep(1000);
        System.out.println("end");

        DeadlockChecker.check();
    }

    static class DeadlockChecker {

        private final static ThreadMXBean mbean = ManagementFactory
                .getThreadMXBean();

        public static void check() {

            Thread tt = new Thread(() -> {
                {
                    while (true) {
                        long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                        if (deadlockedThreadIds != null) {
                            ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                            for (Thread t : Thread.getAllStackTraces().keySet()) {
                                for (int i = 0; i < threadInfos.length; i++) {
                                    if (t.getId() == threadInfos[i].getThreadId()) {
                                        System.out.println(t.getName());
                                        t.interrupt();
                                    }
                                }
                            }
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                }
            });
            tt.setDaemon(true);
            tt.start();
        }

    }
}
