### 并发

> ConcurrentHashMap


> Synchronized

优化后引偏向锁，轻量级锁（自旋锁）性能提高

> ReentrantLock

自旋锁，通过循环调用CAS操作来实现加锁。

> Synchronized和ReentrantLock的区别

    * 都是可重入锁
    * Sync通过jvm实现，ReentrantLock通过jdk实现
    * Sync优化前性能不如ReentrantLock,优化后性能差不多。都是试图在用户态就把加锁问题解决，避免进入内核态的线程阻塞。
    * Sync只能是非公平锁，ReentrantLock可以指定是公平锁或者非公平锁。（公平锁，先等待先获得锁）
    * Sync每次只能随机唤醒一个线程或者全部线程，ReentrantLock可以通过Condition唤醒指定线程。
    * ReentrantLock通过lockInterruptibly()来实现中断等待的线程机制。
    
