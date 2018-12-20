package com.leo.thread;

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
}
