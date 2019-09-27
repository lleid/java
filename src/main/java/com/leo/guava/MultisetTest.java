package com.leo.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

public class MultisetTest {
    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");

        System.out.println("multiset b size :" + multiset.count("b"));
        System.out.println("multiset size :" + multiset.size());

        Set<String> set = multiset.elementSet();
        System.out.println("set size :" + set.size());
        multiset.remove("b", 2);
        System.out.println(multiset.size());

        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println(entry.getElement() + "," + entry.getCount());
        }
    }
}
