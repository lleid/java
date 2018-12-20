package com.leo.thread;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁   
 */
public class ReentrantLockThread extends Thread {

    private static ReentrantLock lock = new ReentrantLock();

    private static int i;

    public ReentrantLockThread(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            lock.lock();
            try {
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " :-->" + j);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockThread j1 = new ReentrantLockThread("j1");
        ReentrantLockThread j2 = new ReentrantLockThread("j2");

        System.out.println(new Date().getTime());
        j1.start();
        j2.start();
        System.out.println(new Date().getTime());
        j1.join();   //j1执行完之后,再执行后续
        System.out.println(new Date().getTime());
        j2.join();   //j2执行完之后,再执行后续

        System.out.println(i);

    }
}
