package com.leo.gc;

/**
 * testGC() 方法执行后，objA和objB会不会被GC呢
 */
public class ReferenceCountingGC {

    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        System.gc();

    }

    public static void main(String[] args) {
        testGC();
    }

}
