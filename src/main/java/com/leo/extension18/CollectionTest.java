package com.leo.extension18;


import com.leo.extension18.lambda.beans.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class CollectionTest {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 10).mapToObj(i -> i).collect(Collectors.toList());
        System.out.println(list);

        Stream.of("a", "b").collect(Collectors.toMap(i -> i, i -> i += "123")).forEach((key, value) -> {
            System.out.println(key + "," + value);
        });

        List<User> users = initList();
        users.stream().collect(HashMap::new, (m, v) -> m.put(v.getName(), v.getAge()), HashMap::putAll).forEach((key, value) -> {
            System.out.println(key + "," + value);
        });

        Map<String, Integer> map = new HashMap<String, Integer>();
        users.forEach(user -> {
            map.put(user.getName(), user.getAge());
        });
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
