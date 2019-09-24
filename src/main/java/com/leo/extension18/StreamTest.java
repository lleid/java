package com.leo.extension18;


import com.leo.extension18.lambda.beans.Bar;
import com.leo.extension18.lambda.beans.Foo;
import com.leo.extension18.lambda.beans.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class StreamTest {
    public static void main(String[] args) {

        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7};
        String[] strings = new String[]{"A", "B", "C"};

        Stream<String> list1 = Stream.of(strings);
        System.out.println(list1.collect(Collectors.toList()));
        List<User> list = initList();

        System.out.println("过滤-找出年祭大于18岁的人");
        list.stream().filter(user -> user.getAge() > 18).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("最大值-找出年纪最大的人");
        User user = list.stream().max(Comparator.comparing(User::getAge)).orElseThrow(() -> new NullPointerException());
        System.out.println(user);

        System.out.println("映射-归纳所有人的年纪总和");
        Optional<Integer> reduce = list.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println("总共：" + reduce.get() + " 岁");

        Map<Integer, List<User>> userMap = list.stream().collect(Collectors.groupingBy(User::getAge));
//        for (Map.Entry<Integer, List<User>> entry : userMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        userMap.forEach((age, users) -> {
            System.out.println(age + "," + users);
        });

        System.out.println("range 初始化数据");
        IntStream.range(1, 4).forEach(System.out::println);

        System.out.println("执行顺序");
        Stream.of("a7", "a2", "c3", "c5").filter(o -> {
            System.out.println("filter :" + o);
            return true;
        }).map(m -> {
            System.out.println("map :" + m);
            return m;
        }).forEach(System.out::println);

        System.out.println("实例化的小技巧");
        List<Foo> foos = new ArrayList<>();

        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo " + i)));
        foos.forEach(foo -> {
            IntStream.range(1, 4).forEach(i -> foo.bars.add(new Bar("Bar " + i)));
        });

        foos.forEach(i -> {
            System.out.println(i);
        });

        foos.stream().flatMap(f -> f.bars.stream()).forEach(i -> System.out.println(i));

        //和上上面一只
        IntStream.range(1, 4).mapToObj(i -> new Foo("Foo " + i)).peek(f -> IntStream.range(1, 4).mapToObj(i -> new Bar("Bar " + i)).
                forEach(f.bars::add)).flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b));

        //reduce 归纳
        list.stream().reduce((u1, u2) -> u1.getAge() > u2.getAge() ? u1 : u2).ifPresent(System.out::println);

        int sum = IntStream.range(1, 10).reduce(0, (i1, i2) -> i1 + i2);
        System.out.println(sum);
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
