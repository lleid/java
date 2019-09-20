package com.leo.generic;

import com.leo.generic.beans.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型，即“参数化类型”。
 * ？ T K V E
 * 泛型参数不能是基本数据类型，编译之后程序去泛型话
 * 也就是说Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
 * 也就是说，泛型信息不会进入到运行时阶段。
 * 静态方法无法访问类上定义的泛型，静态泛型方法static <T> 这个T跟泛型类的T没有关系
 */
public class GenericTest {
    public static void main(String[] args) {
        //不定义确切类型,就是object
        NGeneric n1 = new NGeneric();
        n1.add("123");
        n1.add(123);

        //定义确切类型
        NGeneric<String> n2 = new NGeneric<>();
        n2.add("123");
//        n2.add(123);//报错

        //不定义确切类型,就是object
        IGeneric i1 = new IGeneric() {
            @Override
            public Object next() {
                return "123";
            }
        };

        //定义确切类型
        IGeneric<String> i2 = new IGeneric<String>() {
            @Override
            public String next() {
                return "123";
            }
        };

        IGenerator i3 = new IGenerator();

        System.out.println(i1.next());
        System.out.println(i2.next());
        System.out.println(i3.next());

        //默认是Object级别
        FGeneric.genericI("123");
        FGeneric.genericI(123);

        //方法定义上边界
        FGeneric.genericII(new AA());
        FGeneric.genericII(new AAA());
//        FGeneric.genericII(new A()); //报错

        //方法定义上边界
        FGeneric.genericIIIT(new ArrayList<AAA>() {{
            add(new AAA());
            add(new AAA());
            add(new AAA());
        }});

        //方法定义下边界
        FGeneric.genericIII(new ArrayList<A>() {{
            add(new A());
            add(new A());
            add(new A());
        }});

        //泛型可变参数
        FGeneric.genericIIITI("1", "2", "3");

        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        System.out.println(list1.equals(list2));//true 编译之后程序去泛型话
    }
}
