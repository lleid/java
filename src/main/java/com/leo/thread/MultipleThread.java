package com.leo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 使用wati()和notify()实现消费者和生产者
 */
public class MultipleThread {

    public static void main(String[] args) {
        Queue<Integer> list = new LinkedList<>();
        new Thread(new Producter(list, 10, "producter")).start();
        new Thread(new Consumer(list, 10, "consumer")).start();
    }

    private static class Producter extends Thread {
        private Queue<Integer> list;
        private int max;

        public Producter(Queue<Integer> list, int max, String name) {
            this.max = max;
            this.list = list;
            super.setName(name);
        }

        public void run() {
            while (true) {
                synchronized (list) {
                    if (list.size() == max) {
                        System.out.println("queue is full ,please wait for consume");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("producting value :" + i);
                    list.add(i);
                    list.notifyAll();
                }
            }
        }
    }

    private static class Consumer extends Thread {
        private Queue<Integer> list;
        private int max;

        public Consumer(Queue<Integer> list, int max, String name) {
            this.max = max;
            this.list = list;
            super.setName(name);
        }

        public void run() {
            while (true) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        System.out.println("queue is empty ,please wait for product");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consumeing value :" + list.remove());
                    list.notifyAll();
                }
            }
        }
    }
}
