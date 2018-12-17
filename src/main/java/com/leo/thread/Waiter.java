package com.leo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Waiter implements Runnable {

    private Message message;

    public Waiter(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (message) {
            try {
                System.out.println(name + " waiting to get notified at time  :" +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
                message.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " processed :" + message.getMsg());
        }
    }
}
