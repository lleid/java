package com.leo.lambda;


/**
 * 使用lambda 实现计算器的功能
 */
public class Calculator {
    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinay(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    public static void main(String[] args) {
        Calculator myApp = new Calculator();

        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;

        System.out.println("40 + 2 = " + myApp.operateBinay(40, 2, addition));
        System.out.println("40 - 2 = " + myApp.operateBinay(40, 2, subtraction));
    }
}
