package com.leo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 倒计时器
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        long start = System.currentTimeMillis();
        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        });

        countDownLatch.await();

        System.out.println(countDownLatch.getCount());
        System.out.println(String.format("耗时:%sms", System.currentTimeMillis() - start));

    }
}
