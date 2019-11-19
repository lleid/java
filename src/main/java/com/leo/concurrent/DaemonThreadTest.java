package com.leo.concurrent;

import java.io.IOException;

/**
 * 守护线程，当用户线程执行完成之后，守护线程结束 setDaemon(true)
 */
public class DaemonThreadTest {

    public static void execute() {
        for (int i = 0; ; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(() -> execute());

        thread.setDaemon(true);
        thread.start();

        System.in.read();
    }
}
