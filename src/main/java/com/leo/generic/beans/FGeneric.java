package com.leo.generic.beans;


import java.util.List;

public class FGeneric {

    /**
     * 泛型方法
     *
     * @param t
     * @param <T>
     */
    public static <T> void genericI(T t) {
        System.out.println("genericI");
    }

    /**
     * 泛型方法
     *
     * @param obj
     */
    public static <T extends AA> T genericII(T obj) {
        System.out.println("genericII");
        return null;
    }

    /**
     * 普通方法，使用泛型通配符
     *
     * @param obj
     */
    public static void genericIII(List<? super AA> obj) {
        System.out.println("genericIII");
    }

    /**
     * 普通方法，使用泛型通配符
     *
     * @param obj
     */
    public static void genericIIIT(List<? extends AA> obj) {
        System.out.println("genericIIIT");
    }

    /**
     * 普通方法，泛型可变参数
     *
     * @param args
     */
    public static <T> void genericIIITI(T... args) {

    }

}
