package com.leo.thread;


public class Notifier implements Runnable {

    private Message message;

    public Notifier(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");

        try {
            Thread.sleep(3 * 1000);
            synchronized (message) {
                message.setMsg(name + " notified work done");
                message.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
