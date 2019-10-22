package com.leo.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set是无序的不重复的，一班指的是HashSet
 * LinkedHashSet可以保证添加顺序
 * TreeSet可以保证
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("d");
        System.out.println(set.toString());

        Set<String> set2 = new LinkedHashSet<>();
        set2.add("a");
        set2.add("c");
        set2.add("d");
        set2.add("b");
        set2.add(null);
        set2.add("e");
        System.out.println(set2.toString());

        Set<String> set3 = new TreeSet<>();
        set3.add("a");
        set3.add("d");
        set3.add("c");
        set3.add("b");
//        set3.add(null);//NullPointException

        System.out.println(set3.toString());
    }
}
