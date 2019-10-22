package com.leo.exception;

/**
 * 1.不管try,finally都会执行
 * 2.如果try中有return时，在finally执行前会保存下来，即使finally中有修改也以try中保存值为准，但是如果是引用类型，修改的属性会以finally修改的为准
 * 3.如果finally中有return，直接返回finally中的数据
 */
public class TryCatchTest {
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
    }

    public static int test1() {
        int i = 1;
        try {
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("test1 do finally");
            i = 0;
        }
        return i;
    }

    public static int test2() {
        int i = 1;
        try {
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("test2 do finally");
            i = 0;
            return i;
        }
    }

    public static User test3() {
        User user = new User("u1");
        try {
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("test3 do finally");
            user = new User("u2");
        }
        return null;
    }

    public static User test4() {
        User user = new User("u1");
        try {
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("test4 do finally");
            user.name = "u2";
        }
        return null;
    }
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
