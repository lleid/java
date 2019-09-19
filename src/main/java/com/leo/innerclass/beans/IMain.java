package com.leo.innerclass.beans;

import lombok.Getter;

/**
 * public 公共的
 * protected 子类
 * default 同包
 * private 同类
 */
public class IMain {

    public int o1 = 1;

    @Getter
    private String name = "IMain";

    /**
     * 匿名内部类
     */
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }).start();
    }

    public void go() {
        /**
         * 局部内部类定义在一个方法或者作用域中的类，访问权限仅限于方法内或作用域内
         * 局部内部类就像是方法里面的一个局部变量一样，是不能有 public、protected、private 以及 static 修饰符的。
         */
        class InnerFuc {
            public void print() {
                System.out.println("IMain go InnerFuc");
            }
        }


        InnerFuc fInnerClass = new InnerFuc();
        fInnerClass.print();
    }

    public void getInnerPrivate() {
        new InnerPrivate().go();
    }

    /**
     * 私有 成员内部类， 外部不能访问
     */
    private class InnerPrivate {
        public void go() {
            System.out.println("IMain InnerPrivate go");
        }
    }

    /**
     * 公共成员内部类， 外部能访问 new IMain().new InnerPublic();
     */
    public class InnerPublic {

        public int o1 = 5;//跟外部类重名时，默认访问内部类的成员，如果需要访问外部类成员：外部类.this.变量

        public void go() {
            System.out.println("IMain InnerPublic go IMain.o1 =" + IMain.this.o1 + ",InnerPublic.o1=" + this.o1);

        }
    }

    public static class InnerStatic {
        public void go() {
            System.out.println("IMain InnerStatic go");
        }
    }
}
