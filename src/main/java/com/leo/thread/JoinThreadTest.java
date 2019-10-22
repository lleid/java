package com.leo.thread;

/**
 * thread.join()方法的使用
 */
public class JoinThreadTest extends Thread {

    private String name;
    private int times;

    public JoinThreadTest(String name, int times) {
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
        JoinThreadTest j1 = new JoinThreadTest("j1", 10);
        JoinThreadTest j2 = new JoinThreadTest("j2", 10);

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
