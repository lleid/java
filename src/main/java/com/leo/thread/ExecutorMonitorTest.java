package com.leo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程监控参数
 */
public class ExecutorMonitorTest {

    private static ExecutorService es =
            new ThreadPoolExecutor(50, 100, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(100000));

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            es.execute(() -> {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) es;
        while (true) {
            System.out.println();

            System.out.println("当前排队线程数：" + tpe.getQueue().size());
            System.out.println("当前活动线程数：" + tpe.getActiveCount());
            System.out.println("当前完成线程数：" + tpe.getCompletedTaskCount());
            System.out.println("总线程数：" + tpe.getTaskCount());
        }
    }
}
