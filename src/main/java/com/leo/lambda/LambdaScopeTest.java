package com.leo.lambda;

import java.util.function.Consumer;

/**
 * 封闭范围 访问局部变量
 */
public class LambdaScopeTest {
    public int x = 0;

    class FirstLevel {
        public int x = 1;

        void methodInFirstLevel(int x) {

//            x = 99;    Error:(19, 44) java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量

            Consumer<Integer> myConsumer = (y) -> {     //(x) variable x is already defined in method methodInFirstLevel(int)
                System.out.println("x =" + x);
                System.out.println("y =" + y);
                System.out.println("this.x =" + this.x);
                System.out.println("LambdaScopeTest.x =" + LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);
        }
    }

    public static void main(String[] args) {
        LambdaScopeTest test = new LambdaScopeTest();
        FirstLevel firstLevel = test.new FirstLevel();
        firstLevel.methodInFirstLevel(23);
    }
}
