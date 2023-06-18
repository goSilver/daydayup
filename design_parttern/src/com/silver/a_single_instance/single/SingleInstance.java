package com.silver.a_single_instance.single;

import java.util.concurrent.atomic.AtomicLong;

public class SingleInstance {
}

/**
 * 方式一：饿汉式
 * 饿汉式的实现方式比较简单。在类加载的时候，instance 静态实例就已经创建并初始化好了，
 * 所以，instance 实例的创建过程是线程安全的。
 * 不过，这样的实现方式不支持延迟加载（在真正用到 IdGenerator 的时候，再创建实例）。
 */
class IdGeneratorHungry {
    private AtomicLong id = new AtomicLong();
    private static IdGeneratorHungry instance = new IdGeneratorHungry();

    private IdGeneratorHungry() {
    }

    public static IdGeneratorHungry getInstance() {
        return instance;
    }

    public long getId() {
        return this.id.incrementAndGet();
    }
}

/**
 * 方式二：懒汉式
 * 懒汉式相对于饿汉式的优势是支持延迟加载。
 * 不过懒汉式的缺点也很明显，我们给 getInstance() 这个方法加了一把大锁（synchronzed），导致这个函数的并发度很低。
 */
class IdGeneratorLazy {
    private final AtomicLong id = new AtomicLong();
    private static IdGeneratorLazy instance;

    private IdGeneratorLazy() {
    }

    public static synchronized IdGeneratorLazy getInstance() {
        if (instance == null) {
            instance = new IdGeneratorLazy();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

/**
 * 方式三：双重检测
 * 在这种实现方式中，只要 instance 被创建之后，即便再调用 getInstance() 函数也不会再进入到加锁逻辑中了。所以，这种实现方式解决了懒汉式并发度低的问题。
 * <p>
 * 实际上，上述实现方式存在问题：CPU 指令重排序可能导致在 IdGenerator 类的对象被关键字 new 创建并赋值给 instance 之后，
 * 还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了。这样，另一个线程就使用了一个没有完整初始化的 IdGenerator 类的对象。
 * 要解决这个问题，我们只需要给 instance 成员变量添加 volatile 关键字来禁止指令重排序即可。
 */
class IdGeneratorDoubleCheck{
    private AtomicLong id = new AtomicLong();
    // volatile 禁止指令重排序
    private volatile static IdGeneratorDoubleCheck instance;
    private IdGeneratorDoubleCheck() {}

    public IdGeneratorDoubleCheck getInstance() {
        if (instance == null) {
            // 此处为类级别的锁
            synchronized (IdGeneratorDoubleCheck.class) {
                if (instance == null) {
                    instance = new IdGeneratorDoubleCheck();
                }
            }
        }
        return instance;
    }
}

/**
 * 方式四：静态内部类
 * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象。
 * 只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一性、创建过程的线程安全性，
 * 都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 */
class IdGeneratorStatic {
    private AtomicLong id = new AtomicLong(0);
    private IdGeneratorStatic() {}

    private static class SingletonHolder{
        private static final IdGeneratorStatic instance = new IdGeneratorStatic();
    }

    public static IdGeneratorStatic getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

/**
 * 方式五：枚举
 * 基于枚举类型的单例实现。这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 */
enum IdGeneratorEnum {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}