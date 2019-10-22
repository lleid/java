package com.leo.thread;

import com.leo.extension18.lambda.beans.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GenericThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread1().start();
        new Thread(new Thread2()).start();

        FutureTask<User> futureTask = new FutureTask<User>(new Thread3<>());
        new Thread(futureTask).start();

        User user = futureTask.get();
        System.out.println(user);
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("create by extends Thread");
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("create by extends Runnable");
        }
    }

    static class Thread3<T> implements Callable<T> {

        @Override
        public T call() throws Exception {
            System.out.println("create by extends Callable");
            return (T) new User("123", 123);
        }
    }
}
