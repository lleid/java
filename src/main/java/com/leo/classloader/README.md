### ClassLoader 类加载器

ClassLoader的具体作用就是将class文件加载到jvm虚拟机中去，程序就可以正确运行了。但是，jvm启动的时候，并不会一次性加载所有的class文件，而是根据需要去动态加载。

> Bootstrap ClassLoader

启动类加载器，加载核心类库，%JRE_HOME%\lib\目录下的jar和classes

> Extention ClassLoader

扩展类加载器，加载%JRE_HOME%\lib\ext目录下的jar和classes

> App ClassLoader 

应用加载器，加载当前应用的classpath的jar和classes

### 双亲委派机制

加载类时先判断缓存中是否已经加载，没有找到发给parent进行加载，递归向上查询。当前加载器没有parent时，则通过bootstrap classloader进行加载，如果没有找到，则自己加载。
可以防止核心类被替换。比如自定义一个String类，如果不是双亲委派机制的话，就会覆盖核心类库中的String


###  自定义 Class Loader

可以用于文件加解密

### context classloader 线程上下文加载器

线程默认的是app classloader 可以通过setContextClassLoader修改默认加载器

 
### 参考
> 注意：测试时需要删除beans 下面的文件class。双亲委派机制会影响测试结果。自定义加载器->AppClassLoader。项目中加载器是AppClassLoader。

[一看你就懂，超详细java中的ClassLoader详解](https://blog.csdn.net/briblue/article/details/54973413)
