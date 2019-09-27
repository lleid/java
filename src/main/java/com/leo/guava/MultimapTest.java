package com.leo.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MultimapTest {
    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("upper", "A");
        multimap.put("upper", "D");
        multimap.put("upper", "E");

        Map<String, Collection<String>> map = multimap.asMap();
        map.forEach((k, v) -> System.out.println(k + "," + v));
        multimap.keySet().forEach(System.out::println);
        multimap.values().forEach(System.out::println);

        List<String> lower = (List<String>) multimap.get("lower");
        System.out.println("lower :" + lower);
        System.out.println(multimap.size());
        lower.add("e"); //改变之后multimap 的长度也发生变化
        System.out.println(multimap.size());

    }
}
