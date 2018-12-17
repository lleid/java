package com.leo.thread;

/**
 * 线程中sleep()和wait()的用法
 * 1.sleep() 让出cpu 但是不释放同步资源锁
 * 2.wait() 让出cpu，释放锁  等待notify（）唤醒
 * 3.Thread.Sleep()可以在任何地方使用，wait()只能在同步方法或同步块中使用  （会抛异常）
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        Message message = new Message();
        Waiter waiter = new Waiter(message);
        Notifier notifier = new Notifier(message);


        new Thread(waiter, "waiter1").start();
        new Thread(waiter, "waiter2").start();
        new Thread(notifier, "notifier").start();

        System.out.println("all thread are started");
    }
}
