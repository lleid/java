package com.leo.thread;

@SuppressWarnings("all")
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
//        interrupt1();
//        interrupt2();
//        interrupt3();
        interrupt4();
    }

    /**
     * 中断 失败
     */
    public static void interrupt1() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("interrupt1");
                Thread.yield();
            }
        });

        thread.start();
        thread.interrupt();
    }

    /**
     * 中断 成功
     */
    public static void interrupt2() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("interrupt2");
                Thread.yield();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程中断，程序退出");
                    return;
                }
            }
        });

        thread.start();
        thread.interrupt();
    }

    /**
     * 中断 失败
     * sleep 方法被中断后会去除中断标记，程序继续执行
     *
     * @throws InterruptedException
     */
    public static void interrupt3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("interrupt2");
                Thread.yield();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程中断，程序退出");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被终端，程序退出");
                }
            }
        });

        thread.start();
        thread.sleep(2000);
        thread.interrupt();
    }

    /**
     * 中断 成功
     * sleep 方法被中断后会去除中断标记，程序继续执行
     * 通过手动中断 退出程序
     *
     * @throws InterruptedException
     */
    public static void interrupt4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("interrupt2");
                Thread.yield();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程中断，程序退出");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被终端，程序退出");
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();
        thread.sleep(2000);
        thread.interrupt();
    }
}
