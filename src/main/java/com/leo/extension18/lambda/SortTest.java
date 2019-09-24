package com.leo.extension18.lambda;

import com.leo.extension18.lambda.beans.User;

import java.util.*;

@SuppressWarnings("all")
public class SortTest {
    public static void main(String[] args) {
        List<User> list = initList();


        //jdk1.8之前的排序
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        System.out.println(list);

        //使用lambda排序，带参数类型
        list = initList();
        list.sort(
                (o1, o2) -> o1.getAge().compareTo(o2.getAge())
        );
        System.out.println(list);

        //使用lambda排序，带参数类型 反序
        list = initList();
        list.sort(Comparator.comparing(User::getAge).reversed());
        System.out.println(list);

        //使用lambda排序，先根据年龄后根据姓名
        list = initList();
        list.sort(Comparator.comparing(User::getAge).thenComparing(User::getName));
        System.out.println(list);
    }

    private static List<User> initList() {
        List<User> list = new ArrayList<>();
        list.add(new User("lisa", 23));
        list.add(new User("tom", 11));
        list.add(new User("john", 16));
        list.add(new User("jennis", 26));
        list.add(new User("tin", 26));
        list.add(new User("army", 26));
        list.add(new User("mack", 19));
        list.add(new User("jobs", 65));
        return list;
    }
}
