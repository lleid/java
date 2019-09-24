package com.leo.gc;

/**
 * -verbose:gc -Xss128k
 * 设置栈内存容量
 */
public class JavaVMStackSOF {

    private int statckLength = 1;

    public void statckLeak() {
        statckLength++;
        statckLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.statckLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.statckLength);
            throw e;
        }
    }
}
