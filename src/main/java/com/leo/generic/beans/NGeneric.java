package com.leo.generic.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类
 *
 * @param <T>
 */
public class NGeneric<T> {

    public List<T> data;

    public NGeneric() {
        data = new ArrayList<>();
    }

    public void add(T t) {
        data.add(t);
    }

    public T get(int i) {
        if (data.size() < i || i < 0) {
            return null;
        }
        return data.get(i);
    }

    public void remove(T t) {
        data.remove(t);
    }

    //cannot be refrenced from static context
//    public static void random(T t) {
//    }

    //静态方法上的T跟类的T没有关系，这是一个泛型方法
    public static <T> void random(T t) {
    }

}
