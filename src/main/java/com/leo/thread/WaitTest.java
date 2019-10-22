package com.leo.thread;

@SuppressWarnings("all")
public class WaitTest {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    if (i == 10) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " :" + i);
                }
            }
        });


        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("JAVA 技术栈");
                lock.notifyAll();
            }
        });

        t1.start();
        t2.start();
    }
}
