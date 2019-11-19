### 并发

> 锁

是为了解决并发情况引起的脏读和数据不一致问题

> ConcurrentHashMap


> Synchronized

基于monitor实现同步，每个对象都有一个monitor，一个monitor只能被一个线程拥有。当一个线程执行到monitorenter指令时会尝试获取相应对象的monitor.

* 如果monitor的进入数为0，则该线程可以进入monitor，并将monitor进入数设置为1，该线程即为monitor的拥有者。
* 如果该线程已经拥有该monitor，只是重新进入，则将monitor的进入数加1，所有Synchronized是可重入锁。
* 如果monitor已经被其他线程拥有，则当前线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor。
* 只有拥有相应对象的monitor的线程才能执行monitorexit指令。每执行一次该指令monitor进入数减1，当进入数为0时当前线程释放monitor，此时其他阻塞的线程可以尝试获取monitor。

优化后引偏向锁，轻量级锁（自旋锁）性能提高。

> CAS = CompareAndSwap  比较并交换

原子操作

优点：竞争不大时开销小

缺点: 循环时间长开销大，ABA问题，只能保证一个共享变量的原子操作。

> AQS = AbstractQueuedSynchronizer  队列同步器

用来构建锁和其他同步组件的基础架构，CountDownLatch,ReentrantLock,Semaphore（Sync）,ThreadPoolExecutor(Worker)都有使用。

通过内置的Node（FIFO双向队列）来完成锁进程的排队工作。Node(Thread,Node)

* 同步器包含两个节点类型的的应用，一个指向头节点，一个指向尾节点，未获取到锁的线程会创建节点线程（compareAndSetTail）加入到队列尾部。
* 首节点的线程在释放锁时，将会唤醒后继节点，而后继节点将会在获取锁成功时将自己设置为首节点。

独占式锁获取：有且只有一个线程能获取到锁，ReentrantLock
共享式锁获取: 可以多个线程同时获取到锁，CountDownLatch

> ReentrantLock

自旋锁，通过循环调用CAS操作来实现加锁。

1. 可重入:可以反复得到相同的一把锁，它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放(重入锁)。
2. 可中断:与synchronized不同的是，ReentrantLock对中断是有响应的.synchronized一旦尝试获取锁就会一直等待直到获取到锁。
3. 可限时:超时不能获得锁，就返回false，不会永久等待构成死锁
4. 公平锁:一般意义上的锁是不公平的，不一定先来的线程能先得到锁，后来的线程就后得到锁。不公平的锁可能会产生饥饿现象。公平锁的意思就是，这个锁能保证线程是先来的先得到锁。虽然公平锁不会产生饥饿现象，但是公平锁的性能会比非公平锁差很多。

> semaphore 有什么作用

信号量,可以限制某段代码块的并发数.

> Synchronized和ReentrantLock的区别

* 都是可重入锁
* Sync通过jvm实现，ReentrantLock通过jdk实现
* Sync优化前性能不如ReentrantLock,优化后性能差不多。都是试图在用户态就把加锁问题解决，避免进入内核态的线程阻塞。
* Sync只能是非公平锁，ReentrantLock可以指定是公平锁或者非公平锁。（公平锁，先等待先获得锁）
* Sync每次只能随机唤醒一个线程或者全部线程，ReentrantLock可以通过Condition唤醒指定线程。
* ReentrantLock通过lockInterruptibly()来实现中断等待的线程机制。

> ConCurrentHashMap的并发度是什么

并发度就是Segment的大小,默认是16,这意味着最多同时可以有16个线程操作ConcurrentHashMap,这是ConcurrentHashMap对Hashtable最大的优势

> ReadWriteLock读写锁

读锁共享,写锁独占.读和读之间不互斥,读和写,写和写,写和读之间才会互斥,提高了读写的性能.

> FutureTask是什么

FutureTask表示一个异步运算的任务,有一个泛型参数,可以传入一个Callable的具体实现类,可以对这个异步运算的结果进行等待获取,判断是否完成,取消任务等操作.

> 如何写一个死锁

DeadLockTest.java

1. 两个对象lock1,lock2
2. 线程1先获取lock1对象锁,等待lock2对象锁
3. 线程2先获取lock2对象锁,等待lock1对象锁

> 如何避免死锁

1. 按顺序加锁
2. 获取锁时限,超过一定时间,自动释放
3. 随机等待时间


    
