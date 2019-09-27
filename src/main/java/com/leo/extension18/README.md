### Lambda

1. Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
2. Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
3. 使用 Lambda 表达式可以使代码变的更加简洁紧凑。

### 语法

```
(parameters) -> expression
或
(parameters) ->{ statements; }

parameters是输入参数列表，用逗号分隔
{}代码块
```

### @FunctionalInterface 函数式接口
1. 可以包含default方法和static方法
2. 只能有一个抽象接口
3. 在接口中覆写Object类中的public方法，不算是函数式接口的方法

> 白话：就是使用Lambda表达式 实现匿名内部类 ,从而传递给方法
