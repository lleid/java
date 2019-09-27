package com.leo.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.leo.extension18.lambda.beans.User;

import java.util.Collections;
import java.util.List;

/**
 * 创建不可变集合
 */
public class ImmutableCollectionTest {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c");
        ImmutableList<String> list1 = ImmutableList.copyOf(list);
        ImmutableList<String> list2 = ImmutableList.of("e", "f");
        List<String> list3 = Collections.unmodifiableList(list); //如果修改原集合数据会发生变化
        ImmutableList<User> list4 = ImmutableList.<User>builder().add(new User("123", 123)).build(); // builder 模式创建不可变对象

        System.out.println(list);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println("----------------------");
        list.add("d");
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);

//        list1.add("d");//throw UnSupportOperationException
//        list2.add("d");//throw UnSupportOperationException
//        list3.add("d");//java.lang.UnsupportedOperationException

    }
}
