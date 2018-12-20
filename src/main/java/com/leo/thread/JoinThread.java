package com.leo.thread;

public class JoinThread extends Thread {

    private String name;
    private int times;

    public JoinThread(String name, int times) {
        this.name = name;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(name + " :-->" + i);
        }
    }

    public static void main(String[] args) {
        JoinThread j1 = new JoinThread("j1", 10);
        JoinThread j2 = new JoinThread("j2", 10);

        Thread t1 = new Thread(j1);
        Thread t2 = new Thread(j2);

        t1.start();
        try {
            t1.join();   //t1执行完之后,再执行后续
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main :-->" + i);
        }
    }
}
