package com.leo.collections;

public class NArrayList {

    private Object[] elementData;

    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public NArrayList() {
        this(10);
    }

    public NArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }


    public void add(Object obj){

    }
}
