package com.leo.instance;

/**
 * 单例
 * 线程安全
 */
public enum SingletonEnum {
    INSTANCE;

    public void show() {
        System.out.println("thread-safe enum singleton");
    }

    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        SingletonEnum singleton2 = SingletonEnum.INSTANCE;

        System.out.println(singleton == singleton2);
        singleton.show();

    }
}
