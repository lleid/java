### 集合

#### Set
> HashSet

基于HashMap实现，不允许出现重复数据，不能保证集合中的数据，最多只能有一个null元素

> TreeSet

可以实现自然顺序,不能添加null元素

> LinkedHashSet

可以实现插入顺序

#### List
> ArrayList

内部基于数组实现，可以对元素进行随机访问，插入和删除速度慢

> LinkedList

继承AbstractSequentialList的双向链表

> Vector

矢量队列，线程安全(synchronized方法来实现)。通过数据来保存数据，默认容量是10。

> Collections.SynchronizedList

线程安全（synchronized同步对象锁）

> CopyOnWriteArrayList

线程安全（add加ReentrantLock实现，读不加锁），适用于读多写少的情况。复制再写入，就是在添加元素的时候，先把原 List 列表复制一份，再添加新的元素。

#### Stack

继承Vector,先进后出

#### Map

> HashMap

底层就是一个数组结构（Entry Table）数组的每一项又是一个链表（Bucket，用于解决Hash冲突而设计）。
每个Bucket包含一个Entry(Map自定义的一种结构，包含一个往后的指针)。

> TreeMap

基于红黑树数据结构实现，它们会被排序（次序由Comparable或Comparator决定）。

> WeakHashMap

弱引用Map，如果没有map之外的引用指向某个键，则此键可以被垃圾回收器回收。

> IdentifyHashMap

使用==代替equals()对键作比较的hash map

> Hashtable

继承Dictionary类，实现Map接口，线程安全。

