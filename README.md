### Java

1. switch 支持byte,char,shrot,int及其包装类,枚举，字符串(Jdk1.7) 。
2. UUID(universally unique identifier)通用唯一识别码，32位16进制数字组成。时间+时钟顺序+机器识别码。
3. 字符串拼接+和contact的区别。+两边可以是null,字符串数及其他基本数据类型，contact两边只能是字符串（NullPointException）。
4. void用于无返回值的方法定义，Void是void的包装类，不能被是实例化，Void还可用于一直返回Null的方法和返回Null的泛型。
5. 字符串subString在jdk,6,7,8的差异。String包含三个字段：char value[],int offset,int count;
    * jdk6中subString会创建一个新的字符串，但是字符串中还是指向之前的字符数组，只是数量和下标引用不一样。频繁的调用会导致性能问题，解决方案是subString()+''。
    * jdk7,8中subString会调用Arrays.copyOfRange()重新生成传一个字符串是数组。
6. @interface元注解,自定义注解
    * @Target指定注解的使用范围（类，方法，字段等）例如:ElementType.Field
    * @Retention指定声明周期(源码，class文件，运行时)
    * @Documented指定注解会包含在javadoc中
    * @Inherited指定子类可以继承父类的注解，只能在类上
    * @Native 指定字段是一个常量(Jdk1.8)
    * @Repeatable指定可以使用重复注解(Jdk1.8)
7. transient 
    * transient修饰的变量不能被序列化
    * transient只作用于实现Serializable接口
    * transient只能用来修饰普通成员变量字段
    * 不管有没有transient修饰，静态变量都不能被序列化
8. StringBuffer和StringBuilder的区别
    * 内部和String一样，都是通过一个char[]来存储字符串，不过String的char[]是final不可变的
    * 都继承AbstractStringBuilder
    * StringBuffer通过Synchronized来实现线程安全，StringBuilder非线程安全，并发下会抛ArrayIndexOutOfBoundException。
    （append(str)中通过ensureCapacityInternal()方法用来判断当前对象的容量能不能盛下新的字符串，不够的话新建一个长度为2*len+2的新数组，通过System.arrayCopy将原数组内容复制到新数组中，最后将指针指向新的数组）
9. hashCode和identifyHashCode的区别
   * obj.hashCode(),System.identifyHashCode(obj)
   * identifyHashCode()返回根据对象物理内存地址生成的hash值 
   * 如果对象重写了hashCode()方法,则hashCode()返回重写后的hash值
   * 如果对象没有重写hashCode()方法,则hash值相同



