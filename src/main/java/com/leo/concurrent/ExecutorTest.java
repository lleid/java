package com.leo.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void query1() {
        System.out.println("query 1");
    }

    public static void query2() {
        System.out.println("query 2");
    }

    public static void query3() {
        System.out.println("query 3");
    }

    public static void main(String[] args) {
        ExecutorService excutor = Executors.newFixedThreadPool(3);

        excutor.execute(() -> {
            query1();
        });

        excutor.execute(() -> {
            query2();
        });

        excutor.execute(() -> {
            query3();
        });
    }
}
