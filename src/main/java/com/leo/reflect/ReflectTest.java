package com.leo.reflect;

import com.leo.reflect.beans.Animal;
import com.leo.reflect.beans.IAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 反射:reflect
 * 在运行时，通过反射可以获取类的所有信息
 */
@SuppressWarnings(value = {"all"})
public class ReflectTest {

    /**
     * getDeclared*** 表示声明所有的，但是不包括父类
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String animalCname = "com.leo.reflect.beans.Animal";
        System.out.println(Class.forName(animalCname).getClassLoader());

//        classInstance(animalCname);

//        getInterface(animalCname);
//        getSuper(animalCname);
//        getConstructor(animalCname);
//        getMethod(animalCname);
//        getFiled(animalCname);
//        getAnnotaion(animalCname);
//        getGeneric(animalCname);
    }

    /**
     * 通过 class 进行初始化
     *
     * @param className
     * @throws Exception
     */
    public static void classInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object a1 = clazz.newInstance();
        System.out.println(a1);
    }

    /**
     * 获取所有 constructor
     *
     * @param className
     * @throws Exception
     */
    public static void getConstructor(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?>[] constructors2 = clazz.getDeclaredConstructors();
        for (Constructor c : constructors2) {
            c.setAccessible(true);
            System.out.println(c.getName() + ":" + Arrays.asList(c.getGenericParameterTypes()));
        }

        Constructor c1 = clazz.getDeclaredConstructor();
        Object a1 = c1.newInstance();
        System.out.println(((Animal) a1).getAge());

        Constructor c3 = clazz.getDeclaredConstructor(String.class, int.class);
        Object a3 = c3.newInstance("123", 12);
        System.out.println(((Animal) a3).getName());
    }

    /**
     * 获取所有 接口
     *
     * @param className
     * @throws Exception
     */
    public static void getInterface(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();

        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c.getName());
        }
    }


    /**
     * 获取父类
     *
     * @param className
     * @throws Exception
     */
    public static void getSuper(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Class superClazz = clazz.getSuperclass();
        System.out.println(superClazz.getName());
    }

    /**
     * 获取所有 method
     *
     * @param className
     * @throws Exception
     */
    public static void getMethod(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();

        Method[] methods2 = clazz.getDeclaredMethods();
        for (Method m : methods2) {
            m.setAccessible(true);
            System.out.println(m.getName() + ":" + Arrays.asList(m.getParameterTypes()));
            if (m.getName().equals("setName")) {
                m.invoke(obj, "李四");
            }
        }
        System.out.println(obj);

        //获取指定方法
        Method method = clazz.getMethod("setName", String.class);
        method.invoke(obj, "7788");
        System.out.println(obj);
    }

    /**
     * 获取所有 field
     * .getFiled()获取public成员变量，所有的（包括父类，接口)
     * .getDeclaredFields() 获取声明的所有属性
     *
     * @param className
     * @throws Exception
     */
    public static void getFiled(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();

        Field[] fields2 = clazz.getDeclaredFields();
        for (Field f : fields2) {
            System.out.println(f.getName());
            if (f.getName().equals("name")) {
                f.set(obj, "赵六");
            }
        }
        System.out.println(obj);

        //获取指定属性
        Field field = clazz.getField("name");
        field.set(obj, "5566");
        System.out.println(obj);
    }

    /**
     * 获取所有 注解
     *
     * @param className
     * @throws Exception
     */
    public static void getAnnotaion(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a.annotationType().getName());
        }

        Field[] fields2 = clazz.getDeclaredFields();
        for (Field f : fields2) {
            IAnnotation ia = f.getAnnotation(IAnnotation.class);
            if (f.getAnnotation(IAnnotation.class) != null) {
                f.set(obj, ia.name());
            }

        }

        System.out.println(obj);
    }

    /**
     * 获取 泛型
     *
     * @param className
     * @throws Exception
     */
    public static void getGeneric(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        //获取指定方法
        Method method = clazz.getMethod("doGeneric", HashMap.class);

        Type[] types = method.getGenericParameterTypes();

        if (types == null || types.length > 1) {
            return;
        }

        ParameterizedType parameterizedType = (ParameterizedType) types[0];

        Type rawType = parameterizedType.getRawType();
        System.out.println("rawType :" + rawType);

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments == null || actualTypeArguments.length < 1) {
            return;
        }

        //  打印出每一个类型
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type = actualTypeArguments[i];
            System.out.println("----> type=" + type);
        }
    }

}
