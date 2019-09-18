package com.leo.instance;

/**
 * 单例
 * 饿汉模式 线程安全 没有懒加载
 */
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public Singleton getInstance() {
        return INSTANCE;
    }

    public void show() {
        System.out.println("thread-safe singleton");
    }
}
