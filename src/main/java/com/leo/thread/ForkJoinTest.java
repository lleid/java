package com.leo.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 并行执行任务框架
 *
 * ForkJoin采用工作窃取算法，若一个工作线程的任务队列为空没有任务时，便会从其他工作线程中获取任务主动执行。
 * 为了实现工作窃取，在工作线程中维护了双端队列，窃取任务线程从队尾获取任务，被窃取任务线程从队首获取任务
 * 1. 如果任务拆解的很深，系统内的线程数量堆积，会导致性能严重下降
 * 2. 如果函数调用栈很深，会导致栈内存溢出
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;

    private long start;
    private long end;

    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        test();
        System.out.println("-----------------");
        testForkJoin();
    }

    private static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();

        Long sum = 0L;
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }

        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    private static void testForkJoin() {
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinTest(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }


    @Override
    protected Long compute() {
        long sum = 0;

        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;

            ForkJoinTest task1 = new ForkJoinTest(start, mid);
            task1.fork();

            ForkJoinTest task2 = new ForkJoinTest(mid + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
