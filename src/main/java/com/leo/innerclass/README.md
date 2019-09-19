### 深入理解内部类

> 1.为什么成员内部类可以无条件访问外部类的成员和方法？

外部类和内部类都生成了一个静态方法，返回被调用信息。
内部类中存在一个final类型的外部类成员变量，通过构造方法初始化。所以内部类可以通过final变量访问外部类所有的成员和方法

```
$ javap -c InnerClassTest.class
Compiled from "InnerClassTest.java"
public class com.leo.innerclass.InnerClassTest {
  int field1;

  public com.leo.innerclass.InnerClassTest();
    Code:
       0: aload_0
       1: invokespecial #2                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iconst_1
       6: putfield      #3                  // Field field1:I
       9: aload_0
      10: iconst_2
      11: putfield      #1                  // Field field2:I
      14: new           #4                  // class com/leo/innerclass/InnerClassTest$InnerClassA
      17: dup
      18: aload_0
      19: invokespecial #5                  // Method com/leo/innerclass/InnerClassTest$InnerClassA."<init>":(Lcom/leo/innerclass/InnerClassTest;)V
      22: astore_1
      23: aload_1
      24: invokestatic  #6                  // Method com/leo/innerclass/InnerClassTest$InnerClassA.access$000:(Lcom/leo/innerclass/InnerClassTest$InnerClassA;)I//通过静态方法获取内部类x2的值
      27: istore_2
      28: return

  static int access$100(com.leo.innerclass.InnerClassTest);
    Code:
       0: aload_0
       1: getfield      #1                  // Field field2:I
       4: ireturn
}
```
```
$ javap -c InnerClassTest\$InnerClassA.class
Compiled from "InnerClassTest.java"
public class com.leo.innerclass.InnerClassTest$InnerClassA {
  int x1;

  final com.leo.innerclass.InnerClassTest this$0; //普通的内部类对象会持有外部类对象的引用

  public com.leo.innerclass.InnerClassTest$InnerClassA(com.leo.innerclass.InnerClassTest); //构造方法接受一个InnerClassTest类型的参数，即为对应的外部类对象
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field this$0:Lcom/leo/innerclass/InnerClassTest;
       5: aload_0
       6: invokespecial #3                  // Method java/lang/Object."<init>":()V
       9: aload_0
      10: aload_0
      11: getfield      #2                  // Field this$0:Lcom/leo/innerclass/InnerClassTest;
      14: getfield      #4                  // Field com/leo/innerclass/InnerClassTest.field1:I
      17: putfield      #5                  // Field x1:I
      20: aload_0
      21: aload_0
      22: getfield      #2                  // Field this$0:Lcom/leo/innerclass/InnerClassTest;
      25: invokestatic  #6                  // Method com/leo/innerclass/InnerClassTest.access$100:(Lcom/leo/innerclass/InnerClassTest;)I//通过静态方法获取外部类filed2的值
      28: putfield      #1                  // Field x2:I
      31: return

  static int access$000(com.leo.innerclass.InnerClassTest$InnerClassA);
    Code:
       0: aload_0
       1: getfield      #1                  // Field x2:I
       4: ireturn
}
```

> 2.为什么局部内部类和匿名内部类只能访问局部final变量？
当test()方法执行完毕之后，变量fIntB的生命周期就结束了，而此时Thread对象的生命周期可能还没有结束，那么在Thread的run方法中继续访问变量fIntB就变成不可能了，所以为了保证匿名内部类能访问外部方法中的变量，Java中通过复制的方式来实现。
如果局部变量的值在编译期间就可以确定，则直接在匿名内部里面创建一个拷贝。如果局部变量的值无法在编译期间确定，则通过构造器传参的方式来对拷贝进行初始化赋值。（形参不能确认，局部变量确定）
因为内部类的变量和外部方法中的变量不是同一个变量，当我们对内部类中的变量进行修改时，会造成数据不一致性，所以编译器通过final来实现，内部类不能修改外部方法的变量。
```
$ javap -c ITest.class
Compiled from "ITest.java"
public class com.leo.innerclass.ITest {
  public com.leo.innerclass.ITest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class com/leo/innerclass/ITest
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: bipush        20
      11: invokevirtual #4                  // Method test:(I)V
      14: return

  public void test(int);
    Code:
       0: bipush        10
       2: istore_2
       3: new           #5                  // class java/lang/Thread
       6: dup
       7: new           #6                  // class com/leo/innerclass/ITest$1
      10: dup
      11: aload_0
      12: iload_1
      13: invokespecial #7                  // Method com/leo/innerclass/ITest$1."<init>":(Lcom/leo/innerclass/ITest;I)V
      16: invokespecial #8                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      19: invokevirtual #9                  // Method java/lang/Thread.start:()V
      22: return
}
```
```
$ javap -c ITest\$1.class
Compiled from "ITest.java"
class com.leo.innerclass.ITest$1 implements java.lang.Runnable {

  final int val$fIntA;
  final com.leo.innerclass.ITest this$0;

  com.leo.innerclass.ITest$1(com.leo.innerclass.ITest, int); //形参通过构造方法初始化
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #1                  // Field this$0:Lcom/leo/innerclass/ITest;
       5: aload_0
       6: iload_2
       7: putfield      #2                  // Field val$fIntA:I
      10: aload_0
      11: invokespecial #3                  // Method java/lang/Object."<init>":()V
      14: return

  public void run();
    Code:
       0: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: aload_0
       4: getfield      #2                  // Field val$fIntA:I
       7: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
      10: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      13: bipush        10
      15: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
      18: return
}

```
