package com.leo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sysnchronized 的使用方法
 */
public class SyncThread implements Runnable {

    private Message message;

    public SyncThread(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
//        message.synchronizedMethod();
//        message.synchronizedStaticMethod();
//        message.synchronizedClass();
//        message.synchronizedGetClass();
//        message.synchronizedThis();
        message.synchronizedInstance();
    }

    public static void main(String[] args) {
        Message message = new Message();
        Message message1 = new Message();
        Thread thread1 = new Thread(new SyncThread(message), "thread1");
        Thread thread2 = new Thread(new SyncThread(message), "thread2");
        Thread thread3 = new Thread(new SyncThread(message1), "thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }


    private static class Message {

        private Lock LOCK = new ReentrantLock();

        public String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * 同步普通方法
         * 只能作用于单例上面，非单例不同步
         */
        public synchronized void synchronizedMethod() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " synchronizedMethod");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 同步静态方法
         * 不管对象有多少个实例，都同步
         */
        public synchronized static void synchronizedStaticMethod() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " synchronizedStaticMethod");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 同步类 类锁
         * 同步
         */
        public void synchronizedClass() {
            synchronized (Message.class) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronizedClass");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 同步类 类锁
         * 同步
         */
        public void synchronizedGetClass() {
            synchronized (this.getClass()) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronizedGetClass");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 同步this实例
         * 对象所 同步对象实例
         */
        public void synchronizedThis() {
            synchronized (this) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronizedThis");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 同步对象实例
         * 对象所 同步对象实例
         */
        public void synchronizedInstance() {
            synchronized (LOCK) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronizedInstance");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
