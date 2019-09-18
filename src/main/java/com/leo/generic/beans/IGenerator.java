package com.leo.generic.beans;

import java.util.Random;

/**
 * 泛型实现类
 */
public class IGenerator implements IGeneric<String> {

    String[] arrays = new String[]{"a", "b", "c"};

    @Override
    public String next() {
        Random random = new Random();
        return arrays[random.nextInt(3)];
    }
}
