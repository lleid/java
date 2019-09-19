package com.leo.innerclass;


/**
 * 局部内部类 README.md
 */
public class ITest {
    public static void main(String[] args) {
        ITest test = new ITest();
        test.test(20);
    }

    public void test(final int fIntA) {
        final int fIntB = 10;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(fIntA);
                System.out.println(fIntB);
            }
        }).start();
    }
}
