package com.leo.extension18.lambda;


import com.leo.extension18.lambda.beans.User;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class FunctionTest {
    public static void main(String[] args) {
        List<User> list = initList();

        //构造器引用
        User newUser = User.create(User::new);
        newUser.setAge(1);
        newUser.setName("里斯");
        System.out.println(newUser);

        //静态方法引用
        list.forEach(User::updateName);
        System.out.println(list);

        //普通方法引用
        list.forEach(User::updateAge);
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
