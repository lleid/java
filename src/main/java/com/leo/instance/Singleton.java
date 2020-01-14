package com.leo.instance;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例
 *
 */
public class Singleton {
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.get();
    }

}

/**
 * 懒汉模式-非线程安全
 */
class LazyNoSafeSingleton {

    private LazyNoSafeSingleton() {
    }

    private static LazyNoSafeSingleton instance;

    public static LazyNoSafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazyNoSafeSingleton();
        }
        return instance;
    }
}


/**
 * 懒汉模式-线程安全
 */
class LazySafeSingleton {

    private LazySafeSingleton() {
    }

    private static LazySafeSingleton instance;

    public static synchronized LazySafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazySafeSingleton();
        }
        return instance;
    }
}


/**
 * 饿汉模式-线程安全
 */
class HungrySingleton {

    private HungrySingleton() {
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static synchronized HungrySingleton getInstance() {
        return instance;
    }
}

/**
 * 双重检验锁法-通常线程安全，低概率不安全
 */
class DoubleCheckSingleton {

    private DoubleCheckSingleton() {
    }

    private volatile static DoubleCheckSingleton instance = null;

    public static synchronized DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类-线程安全
 */
class StaticInnerSingleton {

    private StaticInnerSingleton() {
    }

    private static class StaticInnerSingletonHolder {
        private static StaticInnerSingleton innerSingleton = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return StaticInnerSingletonHolder.innerSingleton;
    }
}

/**
 * 枚举-线程安全
 */
enum EnumSingleton {
    INSTANCE;

    public void get() {
    }
}

/**
 * 使用ThreadLocal实现单例-线程安全
 */
class ThreadLocalSingleton {
    private ThreadLocalSingleton() {
    }

    private static final ThreadLocal<ThreadLocalSingleton> INSTANCE
            = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getInstance() {
        return INSTANCE.get();
    }
}

/**
 * 使用cas实现单例-线程安全
 */
class CasSingleton {

    private static final AtomicReference<CasSingleton> INSTANCE = new AtomicReference<CasSingleton>();

    private CasSingleton() {
    }

    public static CasSingleton getInstance() {
        for (; ; ) {
            CasSingleton current = INSTANCE.get();
            if (current != null) {
                return current;
            }

            current = new CasSingleton();

            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }
}
