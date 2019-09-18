package com.leo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PersonDemo {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person() {{
            setAge(20);
            setEmailAddress("1@1.c");
            setGender(Sex.MALE);
        }});
        list.add(new Person() {{
            setAge(17);
            setEmailAddress("1@1.c");
            setGender(Sex.FEMALE);
        }});
        list.add(new Person() {{
            setAge(21);
            setEmailAddress("1@1.c");
            setGender(Sex.FEMALE);
        }});
        list.add(new Person() {{
            setAge(26);
            setEmailAddress("1@1.c");
            setGender(Sex.FEMALE);
        }});

        printPersons(list);
    }

    /**
     * 老版本 筛选 实现方式
     * 单个条件
     *
     * @param roster
     * @param age
     */
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    /**
     * 老版本 筛选 实现方式
     * 多个条件
     *
     * @param roster
     * @param low
     * @param high
     */
    public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    /**
     * 老版本 把校验逻辑放到接口中
     *
     * @param roster
     * @param tester
     */
    public static void processPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }


    /**
     * Predicate<T>.test(T)  return boolean 判定，类似CheckPerson可以实现校验
     * Consumer<T>.accept(T) void 消费者，执行传入的代码块
     *
     * @param roster
     * @param tester
     * @param block
     */
    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    /**
     * Predicate<T>.test(T)  return boolean , 判定，类似CheckPerson可以实现校验
     * Function<X,Y>.apply(X)  return Y
     * Consumer<T>.accept(T) void ,消费者，执行传入的代码块
     *
     * @param roster
     * @param tester
     * @param mapper
     * @param block
     */
    public static void processPersons(List<Person> roster, Predicate<Person> tester, Function<Person, String> mapper, Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    /**
     * Iterable<X>
     * Predicate<T>.test(T)  return boolean , 判定，类似CheckPerson可以实现校验
     * Function<X,Y>.apply(X)  return Y
     * Consumer<T>.accept(T) void ,消费者，执行传入的代码块
     *
     * @param roster
     * @param tester
     * @param mapper
     * @param block
     */
    public static <X, Y> void processElements(Iterable<X> roster, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : roster) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    /**
     * 校验
     *
     * @param roster
     */
    public static void printPersons(List<Person> roster) {


        //1 使用静态内部类 调用 processPersons(roster,tester) ;
        processPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.gender == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });

        System.out.println("-------------");

        //2 使用lamdba 调用 processPersons(roster,tester) ;
        processPersons(roster, (Person p) -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25);

        System.out.println("-------------");


        //3 使用lamdba 调用  processPersons(roster,tester，block) ;
        processPersons(
                roster,
                p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                p -> p.printPerson()
        );

        System.out.println("-------------");

        //4 使用lamdba 调用  processPersons(roster,tester,mapper，block) ;
        processPersons(
                roster,
                p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        System.out.println("-------------");

        //5 使用lamdba 调用  processElements(roster,tester,mapper，block) ;
        processElements(
                roster,
                p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        System.out.println("-------------");

        //6 使用Collection.stream()方法，实现5
        roster.stream()
                .filter(p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
//                .map(p -> p.split("@")[0])
                .forEach(email -> System.out.println(email));

    }

    /**
     * 抽象一个校验的接口
     */
    public interface CheckPerson {
        public boolean test(Person p);
    }

    /**
     * 实现该校验接口，查询男性， 年龄在18-25之间
     */
    public static class CheckPersonEligibleForSelectiveService implements CheckPerson {
        @Override
        public boolean test(Person p) {
            return p.gender == Person.Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25;
        }
    }
}


