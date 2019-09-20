package com.leo.classloader;

import java.lang.reflect.Method;

/**
 * 测试类
 */
@SuppressWarnings("all")
public class ClassLoaderTest {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("sun.boot.class.path"));//bootstrap classloader
//        System.out.println(System.getProperty("java.ext.dirs"));//extention classloader
//        System.out.println(System.getProperty("java.class.path"));//app classloader

//        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        System.out.println("ClassLoader is :" + classLoader.toString()); //app classloder
//        System.out.println("ClassLoader is :" + classLoader.getParent().toString()); //extend classloder
//        System.out.println("ClassLoader is :" + classLoader.getParent().getParent().toString()); //bootstrap classloader (null) NullPointException
//        System.out.println("ClassLoader is :" + int.class.getClassLoader().toString()); //bootstrap classloader (null) NullPointException

        //自定义classloader
//        DiskClassLoader diskClassLoader = new DiskClassLoader("c:\\lib");
//        try {
//            Class c = diskClassLoader.loadClass("com.leo.classloader.beans.Speaker");
//            System.out.println(c.getClassLoader().toString());
//            if (c != null) {
//                Object obj = c.newInstance();
//                Method method = c.getDeclaredMethod("say");
//                method.invoke(obj);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //自定义classloader 加解密
//        DecodeClassLoader decodeClassLoader = new DecodeClassLoader("c:\\lib");
//        try {
//            Class c2 = decodeClassLoader.loadClass("com.leo.classloader.beans.Speaker");
//            System.out.println(c2.getClassLoader().toString());
//            if (c2 != null) {
//                Object obj2 = c2.newInstance();
//                Method method = c2.getDeclaredMethod("say");
//                method.invoke(obj2);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        //测试content classloader
        DiskClassLoader diskClassLoader3 = new DiskClassLoader("c:\\lib\\test");
        try {
            Class c3 = diskClassLoader3.loadClass("com.leo.classloader.beans.ISpeaker");
            System.out.println(c3.getClassLoader().toString());
            System.out.println(c3.getClassLoader().getParent().toString());
            if (c3 != null) {
                Object obj3 = c3.newInstance();
                Method method = c3.getDeclaredMethod("say");
                method.invoke(obj3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DiskClassLoader diskClassLoader4 = new DiskClassLoader("c:\\lib");
        System.out.println("Thread " + Thread.currentThread().getName() + " classloader :" + Thread.currentThread().getContextClassLoader().toString());
        try {
            new Thread(() -> {
                System.out.println("Thread " + Thread.currentThread().getName() + " classloader :" + Thread.currentThread().getContextClassLoader().toString());
                try {
                    Thread.currentThread().setContextClassLoader(diskClassLoader4);//线程默认的类加载是app classloader ，可以通过setContextClassLoader 设置一个classloader
                    ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    Class c4 = cl.loadClass("com.leo.classloader.beans.ISpeaker");
                    System.out.println(c4.getClassLoader().toString());
                    if (c4 != null) {
                        Object obj4 = c4.newInstance();
                        Method method = c4.getDeclaredMethod("say");
                        method.invoke(obj4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
