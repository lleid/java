### 线程

> 创建线程的三种方式
1. extends Thread
2. implements Runable
3. implements Callable

> start()和run()的区别

start()方法属于Thread类，通过sync来同步，里面调用native方法来实现来启动一个线程。
run()方法属于Runnable，数以一个普通方法

> yield()和sleep()的区别

1. yield()，sleep()都能暂停当前线程，sleep可以指定休眠时间，yield则依赖cpu的时间片划分
2. 如果持有锁，都不会释放锁资源
3. yield不能被中断，sleep则可以接受中断

> ReentrantLock  重入锁

1. 可重入:可以反复得到相同的一把锁，它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放(重入锁)。
2. 可中断:与synchronized不同的是，ReentrantLock对中断是有响应的.synchronized一旦尝试获取锁就会一直等待直到获取到锁。
3. 可限时:超时不能获得锁，就返回false，不会永久等待构成死锁
4. 公平锁:一般意义上的锁是不公平的，不一定先来的线程能先得到锁，后来的线程就后得到锁。不公平的锁可能会产生饥饿现象。公平锁的意思就是，这个锁能保证线程是先来的先得到锁。虽然公平锁不会产生饥饿现象，但是公平锁的性能会比非公平锁差很多。
