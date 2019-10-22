package com.leo.collections;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("all")
public class ListTest {

    public static List<String> list = Lists.newArrayList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9");

    public static void main(String[] args) {
//        delete1();
//        delete2();
//        delete3();
//        delete4();
        delete5();
    }

    /**
     * for in 是Iterator的加强版，Iterator.next()会去判断修改的数量和期待修改的数量是否一致，否则报ConcurrentModificationException
     * java.util.ConcurrentModificationException
     */
    public static void delete1() {
        for (String str : list) {
            if (str.contains("b")) {
                list.remove(str);
            }
        }
    }

    /**
     * java.lang.IndexOutOfBoundsException
     */
    public static void delete2() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);

            if (str.contains("b")) {
//                list.remove(i);
            }
        }
    }

    /**
     * 可以正常删除，但是每次调用size()，损耗性能
     */
    public static void delete3() {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            if (str.contains("b")) {
//                list.remove(i);
            }
        }
    }

    /**
     * 可以正常删除
     */
    public static void delete4() {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.contains("b")) {
                iterator.remove();
            }
        }
    }

    /**
     * 通过list删除时会判断，修改的数量和期待修改的数量是否一直，否则报ConcurrentModificationException
     * java.util.ConcurrentModificationException
     */
    public static void delete5() {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.contains("b")) {
                list.remove(str);
            }
        }
    }
}
